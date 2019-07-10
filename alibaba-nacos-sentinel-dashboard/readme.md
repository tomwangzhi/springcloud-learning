### Sentinel是什么？

> 分布式系统的流量防卫兵,主要是为了用来作为服务稳定性保障的。
> 说到稳定性保障我们可能想到是hystrix,Netflix已经宣布对Hystrix停止更新
> 未来选择我们可以选resilience4j，或者是Spring Cloud Alibaba下整合的Sentinel

> 本文概述：Sentinel功能细节比较多，本编文章来讲下限流。
> 把Sentinel整合到Spring Cloud应用中，以及如何使用Sentinel Dashboard来配置限流规则
> 参考：http://blog.didispace.com/spring-cloud-alibaba-sentinel-1/

#### 使用Sentinel实现接口限流

> sentinel的使用分为两部分：
> 1. sentinel-dashboard:与hystrix-dasboard类似，但是功能更强大，提供了流控规则、熔断规则的在线维护等功能
> 2. 客户端整合：每个微服务客户端都需要整合sentinel的客户端封装与配置，
> 才能将监控信息上报给dashboard展示以及实时的更改限流或熔断规则等

#### 如何实现？

> - 部署Sentinel Dashboard
> - 整合Sentinel
> - 配置限流规则
> - 验证限流规则

##### 部署Sentinel Dashboard

- 下载地址：sentinel-dashboard-1.6.0.jar
- 其他版本：Sentinel/releases

> sentinel-dashboard没有像nacos那样通过配置文件来修改外置的配置，而是通过启动的时候在控制台指定参数
> 像启动端口号，登录用户名，登录密码。
> -Dserver.port=8888指定端口
> -Dsentinel.dashboard.auth.username=sentinel 指定登录名
> -Dsentinel.dashboard.auth.password=123456 指定登录密码

##### 整合Sentinel

1. 在Spring Cloud应用的pom.xml中引入Spring Cloud Alibaba的Sentinel模块
```text
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.2</version>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

2. 在Spring Cloud应用中通过spring.cloud.sentinel.transport.dashboard参数配置sentinel dashboard的访问地址，比如：

```text
spring.application.name=alibaba-sentinel-rate-limiting
server.port=8001

# sentinel dashboard
spring.cloud.sentinel.transport.dashboard=localhost:8080

```

3. 创建应用主类，并提供一个rest接口，比如：

```text
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @GetMapping("/hello")
        public String hello() {
            return "didispace.com";
        }

    }

}

```

4. 启动应用，然后通过postman或者curl访问几下localhost:8001/hello接口。
```text
$ curl localhost:8001/hello
didispace.com
```

5. 前面准备好后，我们就可以利用本地启用的sentinel进行接口限流的监控与配置。
启动alibaba-nacos-sentinel-dashboard

> 注意这里整合成功后访问sentinel-dashboard,那些限流规则可能没有显示，需要访问下接口刷新才会出来。


### nacos配置中心添加限流规则

1. 引入依赖
```text
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
        <version>1.5.2</version>
    </dependency>
</dependencies>
```

2. 添加配置信息
```text
spring.application.name=alibaba-sentinel-datasource-nacos
server.port=8003

# sentinel dashboard
spring.cloud.sentinel.transport.dashboard=localhost:8080

# sentinel datasource nacos ：http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-1/
spring.cloud.sentinel.datasource.ds.nacos.server-addr=localhost:8848
spring.cloud.sentinel.datasource.ds.nacos.dataId=${spring.application.name}-sentinel
spring.cloud.sentinel.datasource.ds.nacos.groupId=DEFAULT_GROUP
spring.cloud.sentinel.datasource.ds.nacos.rule-type=flow
```
> spring.cloud.sentinel.datasource.ds.nacos.server-addr：nacos的访问地址，，根据上面准备工作中启动的实例配置
>  spring.cloud.sentinel.datasource.ds.nacos.groupId：nacos中存储规则的groupId
  spring.cloud.sentinel.datasource.ds.nacos.dataId：nacos中存储规则的dataId
  spring.cloud.sentinel.datasource.ds.nacos.rule-type：
  该参数是spring cloud alibaba升级到0.2.2之后增加的配置，用来定义存储的规则类型。所有的规则类型可查看枚举类：org.springframework.cloud.alibaba.sentinel.datasource.RuleType，每种规则的定义格式可以通过各枚举值中定义的规则对象来查看，
  比如限流规则可查看：com.alibaba.csp.sentinel.slots.block.flow.FlowRule
  
3. 启动应用
4. nacos创建限流规则的配置
```text
[
    {
        "resource": "/hello",
        "limitApp": "default",
        "grade": 1,
        "count": 5,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```

> 参数说明：
```text
resource：资源名，即限流规则的作用对象
limitApp：流控针对的调用来源，若为 default 则不区分调用来源
grade：限流阈值类型（QPS 或并发线程数）；0代表根据并发数量来限流，1代表根据QPS来进行流量控制
count：限流阈值
strategy：调用关系限流策略
controlBehavior：流量控制效果（直接拒绝、Warm Up、匀速排队）
clusterMode：是否为集群模式
```

> Sentinel控制台中修改规则：仅存在于服务的内存中，不会修改Nacos中的配置值，重启后恢复原来的值。
>  Nacos控制台中修改规则：服务的内存中规则会更新，Nacos中持久化规则也会更新，重启后依然保持。


### sentinel持久化的方式
- 文件配置
- Nacos配置
-  ZooKeeper配置
- Apollo配置
