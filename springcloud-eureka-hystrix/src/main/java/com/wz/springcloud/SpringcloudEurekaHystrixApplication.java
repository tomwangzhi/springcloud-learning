package com.wz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableEurekaClient  // 注册到配置文件中指定的配置中心
public class SpringcloudEurekaHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaHystrixApplication.class, args);
	}

	@Bean
	@LoadBalanced  // 负载均衡的访问服务
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
