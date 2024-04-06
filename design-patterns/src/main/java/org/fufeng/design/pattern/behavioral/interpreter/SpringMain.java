package org.fufeng.design.pattern.behavioral.interpreter;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 11:25
 */
public class SpringMain {

    public static void main(String[] args) {
        org.springframework.expression.ExpressionParser parser = new org.springframework.expression.spel.standard.SpelExpressionParser();
        org.springframework.expression.Expression exp = parser.parseExpression("100 * 2 + 400 * 1 + 66");
        Integer message = (Integer) exp.getValue();
        System.out.println(message);
    }

}
