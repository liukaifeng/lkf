package com.lkf.swagger.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnablePublishSwaggerConfiguration.class)
public @interface PublishSwagger {
}

