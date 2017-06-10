package com.lkf.druid.autoconfigure;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kaifeng on 2017/5/7.
 */
@Configuration
@AutoConfigureAfter(DruidAutoConfiguration.class)
@EnableTransactionManagement
public class TransactionAutoConfiguration implements TransactionManagementConfigurer {

	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;//数据源
	/**
	 * @param
	 * @return
	 * @throws
	 * @title: annotationDrivenTransactionManager
	 * @description: 配置mybatis事物管理
	 * @author 刘凯峰
	 */
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * @param
	 * @return
	 * @throws
	 * @title: transactionInterceptor
	 * @description: 事务拦截规则配置
	 * @author 刘凯峰
	 */
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionManager(annotationDrivenTransactionManager());
		Properties properties = new Properties();
		properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("select*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("insert*", "PROPAGATION_REQUIRED");
		properties.setProperty("save*", "PROPAGATION_REQUIRED");
		properties.setProperty("add*", "PROPAGATION_REQUIRED");
		properties.setProperty("delete*", "PROPAGATION_REQUIRED");
		properties.setProperty("update*", "PROPAGATION_REQUIRED");
		ti.setTransactionAttributes(properties);
		return ti;
	}

	/**
	 * @return
	 * @throws
	 * @title: transactionAutoProxy
	 * @description: 事物拦截对象自动代理配置
	 * @author 刘凯峰
	 */
	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(false);
		transactionAutoProxy.setBeanNames("*ServiceImpl");
		transactionAutoProxy.setInterceptorNames("transactionInterceptor");
		return transactionAutoProxy;
	}
}
