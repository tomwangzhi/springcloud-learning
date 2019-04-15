package com.wz.springcloud.web;


import com.wz.springcloud.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    ConsumerService consumerService;
    @RequestMapping(value = "/consumer",method = RequestMethod.GET)
    public String hi(@RequestParam String name) {
        return consumerService.consumerHi(name);
    }
}
