package com.wz.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("alibaba-nacos-discovery-server")  // 这里指定服务的名称，有对应的接口实现
public interface HelloFeign {

    @GetMapping(value = "/hello")
    String hello(@RequestParam("name") String name);
}
