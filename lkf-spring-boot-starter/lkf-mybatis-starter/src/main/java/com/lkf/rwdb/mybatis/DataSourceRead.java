package com.lkf.rwdb.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceRead extends DataSourceDruid {
    private String username;
    private String password;
}
