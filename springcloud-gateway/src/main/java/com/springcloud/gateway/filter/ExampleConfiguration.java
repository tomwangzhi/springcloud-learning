package com.springcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class ExampleConfiguration {


    @Bean
    @Order(-1)  // 过滤器执行的顺序
    public GlobalFilter a() {
        return (exchange, chain) -> {
            // 过滤器之前执行逻辑
            System.out.println("----------1预先");
            // 过滤器之后执行
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("-------1");
            }));

        };
    }


    @Bean
    @Order(0)
    public GlobalFilter b(){
        return(exchange,chain)->{
            System.out.println("第一次预过滤");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("第一");
            }));


    };
    }

    @Bean
    @Order(2)
    public GlobalFilter c(){
        return (exchange, chain) -> {
            System.out.println("----------1预先");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("-------1");
            }));

        };
    }


}
