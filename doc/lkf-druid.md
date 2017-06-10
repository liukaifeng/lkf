#商龙云项目须知事项
### 一、分页插件使用说明
#### 1.添加插件依赖
~~~
<dependency>
  <artifactId>lkf-spring-boot-starter</artifactId>
  <groupId>com.lkf</groupId>
  <version>1.0-SNAPSHOT</version>
</dependency>
~~~
#### 2.入参DTO继承PageInDTO
例如：服务商入参
~~~
public class BdServicerDTO extends PageInDTO{}
~~~
#### 3.ServiceImpl中对应方法中配置分页请求信息
~~~
public ServiceResult<PageOutDTO> getServicerList(BdServicerDTO bdServicerDTO) {
        BdServicerPO bdServicerPO = BdServicerConverter.BdServicerDTO2PO(bdServicerDTO);
        Page<List<BdServicerListDTO>> pageResult=PageHelper.startPage(bdServicerDTO.getPage(),bdServicerDTO.getLimit());
        bdServicerDao.getServicerList(bdServicerPO);
        if (pageResult.getResult().isEmpty())
            return ServiceResult.failure(ServiceResultConstant.NO_QUALIFIED_DATA);
        return ServiceResult.success(PageOutDTO.result(pageResult.getPages(),pageResult.getResult()));
    }
~~~
### 二、数据库连接池和事务拦截插件使用说明
#### 1.pom引用
```
 <dependency>
    <groupId>com.lkf</groupId>
    <artifactId>lkf-spring-boot-starter-druid</artifactId>
    <version>${project.parent.version}</version>
 </dependency>
```
#### 2.添加配置文件属性
```
##datasource
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://192.168.12.59:3306/testdb?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=pingtai
spring.datasource.password=pingtai
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# 初始化大小，最小，最大
spring.datasource.initialSize=5
spring.datasource.minIdle=5
spring.datasource.maxActive=20

# 配置获取连接等待超时的时间
spring.datasource.maxWait=60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.timeBetweenEvictionRunsMillis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=SELECT 1 FROM DUAL
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false

# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.poolPreparedStatements=true
spring.datasource.maxPoolPreparedStatementPerConnectionSize=20

# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.filters=stat,wall,log4j
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 合并多个DruidDataSource的监控数据
spring.datasource.useGlobalDataSourceStat=true
```
#### 3.登录druid监控后台
```
启动程序，访问http://ip:端口/druid/index.html
账号：admin
密码：123456
```
#### 4.事务拦截规则

```
类名以【ServiceImpl】为后缀
方法名拦截规则如下：
properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
properties.setProperty("select*", "PROPAGATION_REQUIRED, readOnly");
properties.setProperty("insert*", "PROPAGATION_REQUIRED");
properties.setProperty("save*", "PROPAGATION_REQUIRED");
properties.setProperty("add*", "PROPAGATION_REQUIRED");
properties.setProperty("delete*", "PROPAGATION_REQUIRED");
properties.setProperty("update*", "PROPAGATION_REQUIRED");
```
