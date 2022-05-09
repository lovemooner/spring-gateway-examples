package love.moon.springgateway.service;
import love.moon.springgateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author kk
 * @date 2020/8/12 18:31
 */
@Service
public class GatewayDynamicRouteService implements ApplicationEventPublisherAware {

    @Resource
    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    private ApplicationEventPublisher applicationEventPublisher;


    public List<RouteDefinition> getRouters() {
        Flux<RouteDefinition> routeDefinition= redisRouteDefinitionRepository.getRouteDefinitions();
        List<RouteDefinition> list = new ArrayList<>();
        routeDefinition.subscribe(e->list.add(e));
        return list;
    }

    /**
     * 增加路由
     * @param routeDefinition
     * @return
     */
    public int add(RouteDefinition routeDefinition) {
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return 1;
    }

    /**
     * 更新
     * @param routeDefinition
     * @return
     */
    public int update(RouteDefinition routeDefinition) {
        redisRouteDefinitionRepository.delete(Mono.just(routeDefinition.getId()));
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return 1;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Mono<ResponseEntity<Object>> delete(String id) {
        return redisRouteDefinitionRepository.delete(Mono.just(id)).then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
