package org.fufeng.design.pattern.behavioral.interpreter;

/**
 * 解释器模式
 * {@link java.util.regex.Pattern}
 * {@link org.springframework.expression.ExpressionParser}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 11:02
 */
public class Main {

    public static void main(String[] args) {
        String inputStr = "6 100 11 + *";
        ExpressionParser parser = new ExpressionParser();
        int result = parser.parse(inputStr);
        System.out.println(inputStr + " = " + result);
    }

}
