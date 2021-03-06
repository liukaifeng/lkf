package com.lkf.test.config;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {
    private static final Log logger = LogFactory.getLog(SqlSessionFactoryBean.class);

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    /*typeAliasesPackage 默认只能扫描某一个路径下，
    或以逗号等分割的 几个路径下的内容，不支持通配符和正则，采用重写的方式解决*/
    @Override
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;

        try {
            List<String> result = new ArrayList<>();
            Resource[] resources = resolver.getResources(typeAliasesPackage);
            if (resources != null && resources.length > 0) {
                MetadataReader metadataReader;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        try {
                            result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                        } catch (ClassNotFoundException e) {
                            logger.warn("mybatis type aliases package class match error!", e);
                        }
                    }
                }
            }
            if (result.size() > 0) {
                super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));
            } else {
                logger.warn("no mybatis type aliases package matched :" + typeAliasesPackage);
            }
        } catch (Exception e) {
            logger.error("mybatis type aliases package faild :" + typeAliasesPackage, e);
        }
    }



}

