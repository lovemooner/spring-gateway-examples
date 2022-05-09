//package com.kedacom.galaxy.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
///**
// * @author kk
// * @date 2020/8/18 10:54
// */
//@Component
//@Slf4j
//public class WebFilterSample implements WebFilter {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
//        Object url= serverWebExchange.getAttributes().get("url");
//        log.info("WebFilter is working");
//        //写同一日志
//        return webFilterChain.filter(serverWebExchange);
//    }
//}
