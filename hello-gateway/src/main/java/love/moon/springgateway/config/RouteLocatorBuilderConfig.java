package love.moon.springgateway.config;

import love.moon.springgateway.filter.GatewayFilter100;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lovemooner
 * @date 2020/8/19 11:10
 */
@Configuration
public class RouteLocatorBuilderConfig {

    /**
     * gateway中使用RouteLocator的Bean进行路由转发，将请求进行处理，最后转发到目标的下游服务。
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/cba")
                        .filters(f ->
                                f.filter(new GatewayFilter100())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar")
                        )
                        .uri("https://voice.hupu.com/"))
//                .route(p -> p.predicate(exchange -> exchange.getRequest().getPath().subPath(0).toString().startsWith(("/my1/")))
//                        .uri("http://httpbin.org:80"))
                .build();
    }



}
