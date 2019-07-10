### 构建应用接入Nacos注册中心

#### 环境信息
- nacos:1.1.0
- springboot:2.0.5.RELEASE
- springcloud:Finchley.SR1
- springcloud for aliliba:0.2.2.RELEASE
- java:jdk8
- 操作系统：windows7

### Nacos介绍

Nacos致力于帮助您发现、配置和管理微服务。Nacos提供了一组简单易用的特性集，
帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。Nacos帮助您更敏捷和容易地构建、交付和管理微服务平台。
Nacos是构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。
在接下里的教程中，将使用Nacos作为微服务架构中的注册中心（替代：eurekba、consul等传统方案）以及配置中心（spring cloud config）来使用。

### 安装Nacos

下载地址：https://github.com/alibaba/nacos/releases
- Linux/Unix/Mac：sh startup.sh -m standalone
- Windows：cmd startup.cmd -m standalone
- 启动完成之后，访问：http://127.0.0.1:8848/nacos/
- Nacos 0.8.0以上版本，默认用户名密码为：nacos

### nacos配置持久化
默认情况下，nacos配置中心的配置是存在内存数据库中的，这里我们
说下数据库方式存储数据的配置

1.拷贝安装文件conf/nacos-mysql.sql文件放到mysql数据库中运行，数据库版本需5.6+
2.在conf/application.properties加上数据连接信息配置
  spring.datasource.platform=mysql
  db.num=1
  db.url.0=jdbc:mysql://localhost:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
  db.user=root
  db.password=introcks1234
  
### nacos配置中心原理
1.   

### 项目介绍
本项目是一个服务提供者
