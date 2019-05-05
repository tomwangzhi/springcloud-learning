package com.springcloud.gateway.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void test() {
        System.out.println("hello");
    }
}
