package com.lkf.activiti.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @package: PACKAGE_NAME
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-25 14-30
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lkf.activiti","com.lkf.**"})
@PropertySource("classpath:application.properties")
public class AcivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcivitiApplication.class, args);
    }
}
