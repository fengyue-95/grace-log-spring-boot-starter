package com.fengyue95.autolog.placeholderLog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fengyue
 * @date 2021/10/25
 * 可以通过指定获取登录信息，并通过spel表达式获取登录信息
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PlaceholderLog {

    /**
     * 输出日志
     * 
     * @return
     */
    String content() default "";

    /**
     * 当前用户
     * @return
     */
    Class currentUser();

}
