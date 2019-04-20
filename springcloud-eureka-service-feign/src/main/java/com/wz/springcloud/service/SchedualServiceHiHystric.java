package com.wz.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 断路器执行的类，注册到容器中，当出现服务不可用时，会调用这个方法，返回对应的信息
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,error"+name;
    }
}
