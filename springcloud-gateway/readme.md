### 概述
- 该项目提供了一个建立在Spring Ecosystem之上的API网关。
- Spring Cloud Gateway旨在提供一种简单、高效的方式路由到API
- 提供了横切关注点，像安全性，监控/指标和弹性
### 依赖
- Spring 5
- Spring Boot 2
- Project Reactor

### 项目中引入Spring Cloud Gateway
1. 引入依赖：

~~~
 <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
 </dependency>
    
~~~    
注意：
> 如果引入到项目中想关闭网关功能可以设置属性:spring.cloud.gateway.enabled=false

> 使用该网关需要Spring Boot and Spring Webflux提供 Netty runtime的运行容器，不能像传统的容器那样使用war的形式

### 涉及的几个概念
1. Route:路由网关的基本构建块，由一个id、uri、一系列predicates 和 过滤器 如果聚合谓词为true,则匹配路由
2. 谓词：这是一个 Java 8函数谓词。输入类型是 Spring FrameworkServerWebExchange。这允许开发人员匹配来自HTTP请求的任何内容，例如标头或参数。
3. 过滤器：这些是使用特定工厂构建的 Spring FrameworkGatewayFilter实例。这里，可以在发送下游请求之前或之后修改请求和响应。
            
### 工作流程图
![](spring_cloud_gateway_diagram.png)
客户端向Spring Cloud Gateway发出请求。如果网关处理程序映射确定请求与路由匹配，则将其发送到网关Web处理程序。此处理程序运行通过特定于请求的过滤器链发送请求。滤波器被虚线划分的原因是滤波器可以在发送代理请求之前或之后执行逻辑。执行所有“预”过滤器逻辑，
然后进行代理请求。在发出代理请求之后，执行“post”过滤器逻辑。

> 在没有定义端口的路由中定义的uri 将为http和https uri获取设置默认的80和443

### Route Predicate Factories
Spring Cloud Gateway 包含许多内置的断言工厂类供我们使用。这些断言工厂用不同的属性进行匹配。
多个断言工厂组合用and来组合匹配,一旦匹配上就进行路由

### GatewayFilter工厂
路由过滤器允许以某种方式修改传入的HTTP请求或传出的HTTP响应。
路径过滤器的范围限定为特定路径。Spring Cloud Gateway包含许多内置的GatewayFilter工厂
可以对匹配上的路由请求更改请求头，响应头等这些操作，使用的方式是在.filters前缀属性中配置不同的类型

```
例如  filters.AddRequestHeader=X-Request-Foo,Bar
       filters.AddRequestParameter = foo,bar   
```        

### 全局过滤器
该GlobalFilter接口具有相同的签名GatewayFilter。这些是有条件地应用于所有路线的特殊过滤器。


### 什么时候需要使用？
1. 当需要在接口处理之前做一些业务处理
2. 在请求头和响应头做一些处理
