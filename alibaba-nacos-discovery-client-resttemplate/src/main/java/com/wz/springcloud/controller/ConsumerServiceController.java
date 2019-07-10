package com.wz.springcloud.controller;


import com.wz.springcloud.servcie.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerServiceController {

    @Autowired
    TestService testService;

    @GetMapping(value = "/test")
    public Object consumer() {
        return testService.getTestMessage();
    }

}
