package com.lkf.quartz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * A configuration class for quartz which creates a job factory and a scheduler factory bean,
 * which uses the job factory.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Configuration
public class QuartzConfiguration {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    schedulerFactoryBean.setJobFactory(jobFactory());

    return schedulerFactoryBean;
  }

  @Bean
  public AutowiringSpringBeanJobFactory jobFactory() {
    return new AutowiringSpringBeanJobFactory();
  }
}
