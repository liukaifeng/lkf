package com.lkf.rwdb.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16 0016.
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {
    private final DataSourceWrite dataSourceWrite; //写数据源
    private final DataSourceRead dataSourceRead; //读数据源

    @Autowired
    public DynamicDataSource(DataSourceWrite dataSourceWrite, DataSourceRead dataSourceRead) {
        this.dataSourceWrite = dataSourceWrite;
        this.dataSourceRead = dataSourceRead;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.dataSourceWrite == null) {
            throw new IllegalArgumentException("Property 'dataSourceWrite' is required");
        }
        setDefaultTargetDataSource(dataSourceWrite);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), dataSourceWrite);
        if (dataSourceRead != null) {
            targetDataSources.put(DynamicDataSourceGlobal.READ.name(), dataSourceRead);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();
        if (dynamicDataSourceGlobal == null
                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            return DynamicDataSourceGlobal.WRITE.name();
        }
        return DynamicDataSourceGlobal.READ.name();
    }

}
