package com.wz.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;

    // 消费注册到eureka中hi-service实例
    public String consumerHi(String name) {
        return restTemplate.getForObject("http://SERVICE-HI", String.class);
    }
}
