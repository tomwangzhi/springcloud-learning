package com.wz.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudEurekaClient8763Application {

	@Value("${server.port}")
	String port;
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaClient8763Application.class, args);
	}

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String getHi(@RequestParam String name) {

		return "hi "+ name +"i am"+port;
	}




}
