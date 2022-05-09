package love.moon.springgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author kk
 * @date 2020/8/19 10:20
 */
@Component
@Slf4j
public class GlobalFilterSample implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("GlobalFilter前置逻辑");
        Object token=  exchange.getRequest().getHeaders().get("token");
//        if(token==null){
//            throw new RuntimeException("权限异常");
//        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("GlobalFilter后置逻辑");
        }));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

