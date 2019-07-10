package com.wz.springcloud.controller;

import com.wz.springcloud.feign.HelloFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    HelloFeign helloFeign;

    @GetMapping(value = "/hello")
    public String getHello(@RequestParam("name") String name) {
        return helloFeign.hello(name);
    }
}
