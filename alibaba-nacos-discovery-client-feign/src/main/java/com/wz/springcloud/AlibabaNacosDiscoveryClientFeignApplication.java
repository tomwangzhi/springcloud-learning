package com.wz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // 开启feignclient
public class AlibabaNacosDiscoveryClientFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlibabaNacosDiscoveryClientFeignApplication.class, args);
	}

}
