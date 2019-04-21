package com.wz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication  // springboot核心注解
@EnableZuulProxy   //开启网关功能
@EnableEurekaClient  // 注册到eureka
@EnableDiscoveryClient // 服务发现客户端，可以调用服务
public class SpringcloudEurekaZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaZuulApplication.class, args);
	}

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String getHi() {
		return "hi";
	}

}
