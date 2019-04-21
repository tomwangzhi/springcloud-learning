package com.wz.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringcloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigClientApplication.class, args);
    }

    // 这里通过访问配置中心服务器springcloud-config-server 配置中心然后通过属性sql去数据库查询到dev 和 clientid 并且key等于foo的值
    // 再调用set方法设置到foo属性中
    @Value("${foo}")
    String foo;

    @Value("${test}")
    String test;

    @RequestMapping(value = "/foo",method = RequestMethod.GET)
    public String getFoo() {
        return foo+test;
    }


}
