package com.lkf.job.executor.core.config;

import com.lkf.job.core.executor.JobExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Administrator
 */
@Configuration
@ComponentScan(basePackages = "com.lkf.job.executor.service.jobhandler")
public class JobConfig {
    private Logger logger = LoggerFactory.getLogger(JobConfig.class);


    @Value("${lkf.job.admin.addresses}")
    private String addresses;

    @Value("${lkf.job.executor.appname}")
    private String appname;

    @Value("${lkf.job.executor.ip}")
    private String ip;

    @Value("${lkf.job.executor.port}")
    private int port;

    @Value("${lkf.job.executor.logpath}")
    private String logpath;

    @Value("${lkf.job.accessToken}")
    private String accessToken;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public JobExecutor jobExecutor() {
        logger.info(">>>>>>>>>>> lkf-job config init.");
        JobExecutor jobExecutor = new JobExecutor();
        jobExecutor.setIp(ip);
        jobExecutor.setPort(port);
        jobExecutor.setAppName(appname);
        jobExecutor.setAdminAddresses(addresses);
        jobExecutor.setLogPath(logpath);
        jobExecutor.setAccessToken(accessToken);
        return jobExecutor;
    }

}