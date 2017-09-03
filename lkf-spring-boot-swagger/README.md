# lkf-spring-boot-swagger

将多个独立的微服务API文档整合在一起显示
## 怎么使用？

### 必要条件
- Java 1.8
- Maven
- MongoDB


### 配置MongoDB数据库application.properties:

    无账号和密码：
    spring.data.mongodb.uri=mongodb://127.0.0.1:27017/collectionName
    有账号和密码：
    spring.data.mongodb.uri=mongodb://acount:password@127.0.0.1:27017/collectionName

### 打包并启动
使用以下命令打包和启动

    mvn package && java -jar target/lkf-spring-boot-swagger-1.0.0.jar

## 发布应用的API信息 

1. 构建lkf-swagger-publish-starter到自己的maven仓库


        git clone lkf-swagger-publish-starter
        cd lkf-swagger-publish-starter
        mvn clean install
    
    
2. 添加maven依赖

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>
        <dependency>
            <groupId>com.lkf</groupId>
            <artifactId>lkf-swagger-publish-starter</artifactId>            
            <version>1.0-SNAPSHOT.jar</version>
        </dependency>

3. 在Spring Boot application中添加注解，启用Swagger2并推送API信息到API管理中心


        @EnableSwagger2
        @PublishSwagger
        public class Application {
            ...
        }

4. Add the following properties your application.yml file:


       server.port=3722 #本应用服务端口
       server.context-path=/lkf #本应用服务前缀
       swagger.publish.publish-url=http://localhost:3721/swagger/publish/ #API中心服务地址
       swagger.publish.security-token=6e8f1cc6-3c53-4ebe-b496-53f19fb7e10e #token令牌，结合自己的身份认证系统
       swagger.publish.swagger-url:http://localhost:${server.port}${server.context-path}/v2/api-docs #swagger服务接口地址