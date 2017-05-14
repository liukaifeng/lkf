/**  
 * @Title:  App.java
 * @Package com.lemo.demo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author administrator
 * @date  2017年3月7日 下午5:09:39
 * @version V1.0  
 * Update Logs:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 ******************************************************
 */
package com.lkf.example.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * @ClassName: App
 * @Description: springboot启动入口
 * @author administrator
 * @date 2017年3月7日 下午5:09:39
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lkf.example","com.lkf.**"})
@MapperScan("com.lkf.example.**.dao")
public class App
{
    public static void main(String[] args)
    {
    	ApplicationContext app=  SpringApplication.run(App.class, args);

    }
    
    
}
