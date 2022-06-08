package love.moon.springgateway.route;

import com.google.common.collect.Lists;
import love.moon.springgateway.util.JsonUtils;
import love.moon.springgateway.util.RedisUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author kk
 * @date 2020/8/12 18:19
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 存储的的key
     */
    private final static String KEY = "gateway_dynamic_route";

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取路由信息
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> gatewayRouteEntityList = Lists.newArrayList();
        redisUtils.hgets(KEY).stream().forEach(route -> {
            RouteDefinition result = JsonUtils.jsonToObject(route.toString(), RouteDefinition.class);
            gatewayRouteEntityList.add(result);
        });
        return Flux.fromIterable(gatewayRouteEntityList);
    }

    /**
     * 新增
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(KEY, routeDefinition.getId(), JsonUtils.objectToJson(routeDefinition));
            return Mono.empty();
        });
    }

    /**
     * 删除
     * @param routeId
     * @return
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisUtils.hHashKey(KEY, id)) {
                redisUtils.hdel(KEY, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("route definition is not found, routeId:" + routeId)));
        });
    }
}


