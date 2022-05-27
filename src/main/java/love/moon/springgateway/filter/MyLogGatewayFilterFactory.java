package love.moon.springgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义的GatewayFilterFactory ，可应用在yml中配置路径的filters中
 *
 * @author lovemooner
 * @date 2020/8/19 15:16
 */
@Component
@Slf4j
public class MyLogGatewayFilterFactory extends AbstractGatewayFilterFactory<MyLogGatewayFilterFactory.Config> {

    private static final String _TIME = "request_start_time";

    public MyLogGatewayFilterFactory() {
        // MyGatewayFilterFactory，否则会报告ClassCastException
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(_TIME);
                        log.info("MyGatewayFilterFactory,请求地址：{},消耗时间：{}ms", exchange.getRequest().getURI(), System.currentTimeMillis() - startTime);
                    })
            );
        };
    }

    public static class Config {
    }
}
