package com.wz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 开启服务发现客户端来进行服务的调用
@EnableFeignClients     // 开启Feign的客户端功能
public class SpringcloudEurekaServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaServiceFeignApplication.class, args);
	}

}
