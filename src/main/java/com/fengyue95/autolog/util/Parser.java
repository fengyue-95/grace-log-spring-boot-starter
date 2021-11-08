package com.fengyue95.autolog.util;

import java.util.HashMap;

import com.fengyue95.autolog.dto.Person;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author fengyue
 * @date 2021/11/8
 */
public class Parser {

    public static void main(String[] args) {
        ExpressionParser parser =new SpelExpressionParser();
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        Person person = new Person("zhangsan", 181);
        // 将自定义参数添加到上下文  ,我#person.name + '的年龄是:' + #person.age
        standardEvaluationContext.setVariable("person",person);
        Expression expression = parser.parseExpression("我#{#person.name + '的年龄是:' + #person.age}", new TemplateParserContext());
        String expressionValue = expression.getValue(standardEvaluationContext,String.class);
        System.out.println(expressionValue);
    }
}
