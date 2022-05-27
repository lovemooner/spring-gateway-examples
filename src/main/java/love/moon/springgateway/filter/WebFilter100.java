package love.moon.springgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author lovemooner
 * @date 2020/8/18 10:54
 */
@Component
@Slf4j
public class WebFilter100 implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        Object url= serverWebExchange.getAttributes().get("url");
        log.info("WebFilter is working");

        return webFilterChain.filter(serverWebExchange);
    }
}
