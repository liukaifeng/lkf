package com.lkf.quartz.configuration;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Autowires beans to new created job instances.
 *
 * @author liukaifeng
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
  private transient ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    Object jobInstance = super.createJobInstance(bundle);
    applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);

    return jobInstance;
  }
}
