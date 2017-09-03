package com.lkf.test.config;

import com.lkf.swagger.config.PublishSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Administrator on 2017/8/19 0019.
 */
@EnableSwagger2
@PublishSwagger
@SpringBootApplication
@ComponentScan(basePackages = {"com.lkf.test","com.lkf.**"})
@MapperScan("com.lkf.test.dao")
public class Application {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}
