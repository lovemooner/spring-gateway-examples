package love.moon.springgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @author kk
 * @date 2020/8/18 9:34
 */
@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/rf1"), request -> ServerResponse.ok().body(Mono.just("Hello rf1!"), String.class))
                .andRoute(RequestPredicates.GET("/rf2"), request -> ServerResponse.ok().body(Mono.just("See rf2!"), String.class));
    }

}