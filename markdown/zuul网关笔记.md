### zuul网关笔记

#### 简单分布式服务架构图

![](image/zuul.png)

> 概述：在微服务架构中，
> 使用Eurake完成了服务的注册和服务发现
> 使用Ribbon或者Feign完成的服务的远程调用与负载均衡
> 使用Spring Cloud Config实现了应用多环境的外部化配置以及版本管理
> 使用Hystrix的融断机制来避免在微服务架构中个别服务出现异常时引起的故障蔓延

> 上面架构不足之处，值接暴露了部分服务给对外使用
> 这时就可以再添加一层，网关层
> 服务网关是微服务架构中一个不可或缺的部分。通过服务网关统一向外系统提供REST API的过程中，
> 除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能,将非业务相关的功能迁移到
> 网关路由层

#### 开始使用zuul
> 加入依赖
```text
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```
> 注意：如果不是通过serviceId的方式，eureka依赖可以不用，但是一般还是需要加上

> 应用主类
```text
@EnableZuulProxy
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
```
> 注意：这里用了@SpringCloudApplication注解，之前没有提过，通过源码我们看到，
> 它整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker

> 配置文件
```text
spring.application.name=api-gateway
server.port=5555
```

#### zuul网关原理

> 包含了路由和过滤两大功能
> 路由功能是负责将外部的请求转发到具体的服务实例上面去
> 过滤功能主要对请求的过程进行处理，实现请求的校验、服务聚合功能
> 路由映射主要通过pre类型的过滤器完成，它将请求路径与配置的路由规则进行匹配，
> 以找到需要转发的目标地址；而请求转发的部分则是由route类型的过滤器来完成，
> 对pre类型过滤器获得的路由地址进行转发。所以，过滤器可以说是Zuul实现API网关功能最为核心的部件，
> 每一个进入Zuul的HTTP请求都会经过一系列的过滤器处理链得到请求响应并返回给客户端

> 过滤器：
> 实现的过滤器必须包含4个基本特征：过滤类型、执行顺序、执行条件、具体操作。
> 这些元素看着似乎非常的熟悉，实际上它就是ZuulFilter接口中定义的四个抽象方法：
```text
String filterType();
    
int filterOrder();
    
boolean shouldFilter();
    
Object run();
```




