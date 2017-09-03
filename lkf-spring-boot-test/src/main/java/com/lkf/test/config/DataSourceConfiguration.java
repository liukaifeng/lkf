package com.lkf.test.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

@Configuration
public class DataSourceConfiguration {
    private Logger logger= LogManager.getLogger(this.getClass());
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource masterDataSource() {
        logger.info("masterDataSource");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource() {
        logger.info("slaveDataSource");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
