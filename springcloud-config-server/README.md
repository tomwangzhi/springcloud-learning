参考：https://www.fangzhipeng.com/springcloud/2019/02/21/config-jdbc.html
#### 配置文件中心
1. 存在项目的目录工程下面
2. 存在git仓库中进行版本控制
3. 存在数据库，然后再从数据库中查询

#### 流程
> Config server暴露Http api接口
> Config client 通过调用Config Server的Http Api接口来读取配置Config Server配置信息
> Config server从数据中读取应用需要的具体的配置信息。

#### 步骤
1. 搭建springcloud-server组件
2. 初始化数据库和表
3. 搭建springcloud-client组件

#### 开始搭建
> 环境：
1. Spring Boot版本为2.1.4.RELEASE。
2. Spring Cloud版本为Greenwich.SR1

> 组成：
1. config-server工程
2. config-client工程

> 过程：
1. config-server工程需要连接Mysql数据库，读取配置；
2. config-client则在启动的时候从config-server工程读取
工程	描述
config-server	端口8769，从数据库中读取配置
config-client	端口8083，从config-server读取配置

> 搭建config-server工程
1. 创建子模块组件，依赖为jdbc mysql 
2. 创建配置文件application.yml
   
   属性解释：
   spring.profiles.active为spring读取的配置文件名从数据库中读取，必须为jdbc
   spring.datasource配置了数据库相关的信息
   spring.cloud.config.label读取的配置的分支 这个需要在数据库中数据对应
   spring.cloud.config.server.jdbc.sql为查询数据库的sql语句，该语句的字段必须与数据库的表字段一致。
   
   
3. 在程序的启动文件ConfigServerApplication加上@EnableConfigServer注解，开启ConfigServer的功能

> 搭建config-client
1. 在程序的启动配置文件 bootstrap.yml做程序的相关配置，
一定要是bootstrap.yml，
不可以是application.yml，bootstrap.yml的读取优先级更高  

2. 配置解释：

其中spring.cloud.config.uri配置的config-server的地址，
spring.cloud.config.fail-fast配置的是读取配置失败后，执行快速失败。
spring.profiles.active配置的是spring读取配置文件的环境。

 
   
   
