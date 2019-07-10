package com.wz.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebClientController {

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping(value = "/webclient")
    public  Mono<String> getInfo() {
        // 采用异步的方式的请求
        WebClient build = webClientBuilder.build();
        Mono<String> stringMono = build.get().uri("http://alibaba-nacos-discovery-server/hello?name=webclient")
                .retrieve().bodyToMono(String.class);
        return stringMono;
    }
}
