package com.wz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // 声明是Eureka服务端
public class SpringcloudEurekaApplication {

	public static void main(String[] args) {
		// devtools代码更新重启
//		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(SpringcloudEurekaApplication.class, args);
		System.out.println(11111111);
		System.out.println(3434);

	}

}
