package com.lkf.job.core.handler.annotation;

import java.lang.annotation.*;

/**
 * Created by lkf on 2017/11/12 0012.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHandler {
    String value() default "";
}
