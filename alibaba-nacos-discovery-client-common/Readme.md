### 项目介绍
- 本项目是用来消费注册到nacos的服务alibaba-nacos-discovery-server
### 项目搭建步骤
1. 创建一个Spring Boot应用，可以命名为：alibaba-nacos-discovery-server。
2. 编辑pom.xml，加入必要的依赖配置
3. 创建应用主类，并实现一个HTTP接口，在该接口中调用服务提供方的接口。
4. 配置服务名称和Nacos地址，让服务消费者可以发现上面已经注册到Nacos的服务。
5. 启动服务消费者，然后通过curl或者postman等工具发起访问

