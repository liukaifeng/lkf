package com.lkf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Administrator on 2017/6/10 0010.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lkf.**"})
@PropertySource(value = "/conf/application.properties")
public class Application {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
    }

}
