package com.wz.springcloud;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

// 开启Spring Cloud的服务注册与发现 注册到nacos的服务
// 引入了spring-cloud-starter-alibaba-nacos-discovery模块
// Spring Cloud Common中定义的那些与服务治理相关的接口将使用Nacos的实现，类似的服务注册于发现还有consul euraka

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosDiscoveryServerApplication {

	@NacosInjected
	private NamingService nameService;

	private static NamingService namingService;

	public static void main(String[] args) {
		SpringApplication.run(AlibabaNacosDiscoveryServerApplication.class, args);
	}

	@PostConstruct
	public void init() {
		namingService = nameService;
	}

	@Slf4j
	@RestController
	static class TestController {
		@GetMapping("/hello")
		public String hello(@RequestParam String name) {
			log.info("invoked name = " + name);
			return "hello " + name;
		}



		@RequestMapping(value = "/get", method = RequestMethod.GET)
		@ResponseBody
		public List<Instance> get(@RequestParam String serviceName) throws NacosException {
			return namingService.getAllInstances(serviceName);
		}
	}

}
