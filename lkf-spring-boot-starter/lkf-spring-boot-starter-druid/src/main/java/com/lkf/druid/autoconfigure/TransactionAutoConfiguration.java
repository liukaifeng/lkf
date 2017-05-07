package com.lkf.druid.autoconfigure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kaifeng on 2017/5/7.
 */
@Configuration
@AutoConfigureAfter(DruidAutoConfiguration.class)
@EnableTransactionManagement
public class TransactionAutoConfiguration implements TransactionManagementConfigurer {

	@Autowired
	private DataSource dataSource;//数据源
	/**
	 * @param
	 * @return
	 * @throws
	 * @Title: annotationDrivenTransactionManager
	 * @Description: 配置mybatis事物管理
	 * @author administrator
	 */
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * @param
	 * @return
	 * @throws
	 * @Title: transactionInterceptor
	 * @Description: 事务拦截规则配置
	 * @author administrator
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
	 * @Title: transactionAutoProxy
	 * @Description: 事物拦截对象自动代理配置
	 * @author administrator
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
