package com.fengyue95.autolog.methodParamLog.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import com.fengyue95.autolog.methodParamLog.cache.LoggerCache;
import org.springframework.util.StopWatch;

/**
 * @author fengyue
 * @date 2021/5/21
 */
@Aspect
@Component
public class MethodParamLogAspect {

    @Pointcut("@annotation(com.fengyue95.autolog.methodParamLog.annotation.MethodParamLog)")
    public void log() {
    }

    @Around(value = "log()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解所在的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取注解所在的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Logger logger = LoggerCache.getLogger(className);
        logger.info("className:{} method:{},方法执行", className, method.getName());

        Object[] args = joinPoint.getArgs();
        logger.info("className:{}, params:{}", className, JSON.toJSONString(args));
        // 执行方法获取返回值
        Object proceed = null;
        StopWatch sw = new StopWatch();
        try {
            sw.start();
            proceed = joinPoint.proceed();
            sw.stop();
        } catch (Exception e) {
            // 如果捕获到异常则打印日志并继续抛出,让业务感知异常的存在
            logger.warn("classname:{},exception:{}", e.getClass().getName(), e);
            throw e;
        }
        // 记录日志
        logger.info("classname:{},return:{}", className, JSON.toJSONString(proceed));
        logger.info("classname:{},totalTime:{}", className, sw.getTotalTimeMillis()+"ms");

    }
}
