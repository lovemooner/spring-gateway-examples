package love.moon.springgateway.controller;

import love.moon.springgateway.route.RedisRouteDefinitionRepository;
import love.moon.springgateway.service.GatewayDynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author kk
 * @date 2020/8/12 18:32
 */
@RestController
@RequestMapping("/gateway")
@Slf4j
public class GatewayDynamicRouteController {

    @Resource
    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    @Resource
    private GatewayDynamicRouteService gatewayDynamicRouteService;

    @GetMapping("/routers")
    public List<RouteDefinition> getRouters() {
        return gatewayDynamicRouteService.getRouters();
    }

    @PostMapping("/add")
    public String create(@RequestBody RouteDefinition entity) {
        int result = gatewayDynamicRouteService.add(entity);
        return String.valueOf(result);
    }

    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition entity) {
        int result = gatewayDynamicRouteService.update(entity);
        return String.valueOf(result);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return gatewayDynamicRouteService.delete(id);
    }

}
