package com.fengyue95.autolog.placeholderLog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fengyue
 * @date 2021/10/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PlaceholderLog {

    /**
     * 输出日志
     * @return
     */
    String content() default "";

}
