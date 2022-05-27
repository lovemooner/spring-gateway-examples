package love.moon.springgateway.route.predicate;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @author lovemooner
 * @date 2020/8/18 15:12
 */
@Component
public class MyPredicateFactory extends AbstractRoutePredicateFactory<MyPredicateFactory.Config> {

    public MyPredicateFactory(){
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                System.out.println(config.getPath());
                System.out.println("PredicateFactory test");
                return false;
            }
        };
    }

    @Data
    static class Config{
        private String path;
    }
}
