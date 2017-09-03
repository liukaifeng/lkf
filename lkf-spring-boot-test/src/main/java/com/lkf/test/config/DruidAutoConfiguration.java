/**  
 * @Title:  DruidConfig.java
 * @Package com.lemo.demo.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author administrator
 * @date  2017年3月7日 下午5:26:06
 * @version V1.0  
 * Update Logs:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 ******************************************************
 */
package com.lkf.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName: DruidAutoConfiguration
 * @Description: 数据库连接池自动配置
 * @author administrator
 * @date 2017年3月7日 下午5:26:06
 *
 */
@Configuration
@AutoConfigureBefore(MybatisConfiguration.class)
public class DruidAutoConfiguration
{
    /**
    * @Title: druiDataSource
    * @Description: 初始化数据源
    * @author administrator
    * @return
    * @throws
    */
    @Bean("druiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druiDataSource()
    {
        return new DruidDataSource();
    }

    /**
     * @Title: DruidStatViewServle2
     * @Description: 注册一个StatViewServlet,配置druid的监控页面
     * @author administrator
     * @return
     * @throws
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet()
    {
        // org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
        // permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * @Title: statFilter
     * @Description: 配置druid的过滤规则
     * @author administrator
     * @return
     * @throws
     */
    @Bean
    public FilterRegistrationBean druidStatFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");// 添加过滤规则
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
