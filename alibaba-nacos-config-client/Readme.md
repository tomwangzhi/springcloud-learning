### nacos的配置中心管理
添加配置修改配置在web界面进行，
使用Spring Cloud Alibaba的Nacos客户端模块来加载配置

1. 引入配置相关的依赖
2. 像正常spring那样进行属性的注入
3. @RefreshScope注解放到类上面表示nacos上的配置变更时刷新

#### nacos配置加载规则
在采用默认值的应用要加载的配置规则就是：
Data ID=${spring.application.name}.properties，Group=DEFAULT_GROUP。

在本组件中就采用的默认的配置规则，data id 采用的是应用名+properties
spring.cloud.nacos.config.group 属性也没有指定

Data ID：alibaba-nacos-config-client.properties
Group：DEFAULT_GROUP
拆解一下，主要有三个元素，它们与具体应用的配置内容对应关系如下：

Data ID中的alibaba-nacos-config-client：对应客户端的配置spring.cloud.nacos.config.prefix，默认值为${spring.application.name}，即：服务名
Data ID中的properties：对应客户端的配置spring.cloud.nacos.config.file-extension，默认值为properties
Group的值DEFAULT_GROUP：对应客户端的配置spring.cloud.nacos.config.group，默认值为DEFAULT_GROUP

#### 使用样例

spring.cloud.nacos.config.prefix=example # 加载DEV_GROUPexample.yaml的配置内容
spring.cloud.nacos.config.file-extension=yaml
spring.cloud.nacos.config.group=DEV_GROUP

### 代码形式发布配置

参见ConfigExample这个类里面写的代码

#### 多环境管理
1. 使用Data ID与profiles实现
实际上，Data ID的规则中，还包含了环境逻辑，这一点与Spring Cloud Config的设计类似。
我们在应用启动时，可以通过spring.profiles.active来指定具体的环境名称，
此时客户端就会把要获取配置的Data ID组织为：
${spring.application.name}-${spring.profiles.active}.properties。
在应用的配置中我们利用属性spring.profiles.active=DEV来指定不同环境的配置开发环境或者测试环境

2. 使用Group实现
Group在Nacos中是用来对Data ID做集合管理的重要概念。
所以，如果我们把一个环境的配置视为一个集合，那么也就可以实现不同环境的配置管理
通过在bootstrap.properties属性文件中配置
Data Id可以配置成一样的为应用程序名
spring.cloud.nacos.config.group=DEV_GROUP
spring.cloud.nacos.config.group=TEST_GROUP

3. 使用Namespace实现
不同的命名空间下，可以存在相同的Group或Data ID的配置。Namespace的常用场景之一是不同环境的配置的区分隔离，
例如：开发测试环境和生产环境的资源（如配置、服务）隔离等。
通过配置来指定运行的命名空间
spring.cloud.nacos.config.namespace=83eed625-d166-4619-b923-93df2088883a

> 注意：不论用哪一种方式实现。对于指定环境的配置
>（spring.profiles.active=DEV、spring.cloud.nacos.config.group=DEV_GROUP、
>spring.cloud.nacos.config.namespace=83eed625-d166-4619-b923-93df2088883a），
都不要配置在应用的bootstrap.properties中。
> 而是在发布脚本的启动命令中，用-Dspring.profiles.active=DEV的方式来动态指定，会更加灵活！。