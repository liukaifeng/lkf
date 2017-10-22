package com.lkf.quartz.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * The application class for the quartz configuration.
 *
 * @author liukaifeng
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
@AutoConfigureAfter({org.springframework.web.WebApplicationInitializer.class})
@Import({QuartzConfiguration.class})
public class QuartzAutoConfiguration implements ApplicationListener<ContextRefreshedEvent>, Ordered {

  private static final Logger LOG = LoggerFactory.getLogger(QuartzAutoConfiguration.class);

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    LOG.info("Quartz configuration loaded.");
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
