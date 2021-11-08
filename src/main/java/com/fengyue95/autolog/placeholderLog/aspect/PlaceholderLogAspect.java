package com.fengyue95.autolog.placeholderLog.aspect;

import java.lang.reflect.Method;

import com.fengyue95.autolog.methodParamLog.cache.LoggerCache;
import com.fengyue95.autolog.placeholderLog.annotation.PlaceholderLog;
import com.fengyue95.autolog.util.SpringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author fengyue 支持识别指定类以及方法入参对象属性获取，打印日志
 * @date 2021/11/4
 */
@Aspect
@Component
public class PlaceholderLogAspect {

    private final ExpressionParser                          parser     = new SpelExpressionParser();
    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.fengyue95.autolog.placeholderLog.annotation.PlaceholderLog)")
    public void log() {
    }

    @Around(value = "log()")
    public void logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解所在的类名
        String className = joinPoint.getTarget().getClass().getName();
        Logger logger = LoggerCache.getLogger(className);

        // 获取注解所在的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取注解，解析注解content参数
        EvaluationContext context = new StandardEvaluationContext();
        PlaceholderLog annotation = method.getAnnotation(PlaceholderLog.class);
        String content = annotation.content();

        // 解析入参
        bindMethodParam(method, joinPoint.getArgs(), context);

        Class clazz = annotation.currentUser();
        bindCurrentUser(clazz, context);

        // 根据spel表达式获取值
        Expression expression = parser.parseExpression(content, new TemplateParserContext());
        Object key = expression.getValue(context);
        logger.info("className:{},{}", className,key);
        joinPoint.proceed();
    }

    /**
     * 将方法的参数名和参数值绑定
     *
     * @param method 方法，根据方法获取参数名
     * @param args 方法的参数值
     * @return
     */
    private void bindMethodParam(Method method, Object[] args, EvaluationContext context) {
        // 获取方法的参数名
        String[] params = discoverer.getParameterNames(method);
        // 将参数名与参数值对应起来
        for (int len = 0; len < args.length; len++) {
            context.setVariable(params[len], args[len]);
        }
    }

    /**
     * 将方法的参数名和参数值绑定
     *
     * @param clazz 方法，根据方法获取参数名
     * @return
     */
    private void bindCurrentUser(Class clazz, EvaluationContext context) {
        // 获取传入的上下文对象
        Object bean = SpringUtil.getBean(clazz);
        String[] split = clazz.getName().split("\\.");
        String application = split[split.length - 1];
        String name = application.substring(0, 1).toLowerCase() + application.substring(1);
        // 将参数名与参数值对应起来
        context.setVariable(name, bean);

    }
}
