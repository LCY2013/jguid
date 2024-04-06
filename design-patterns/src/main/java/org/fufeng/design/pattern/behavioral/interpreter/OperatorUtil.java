package org.fufeng.design.pattern.behavioral.interpreter;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 11:17
 */
public class OperatorUtil {

    public static Interpreter getExpressionObject(Interpreter first, Interpreter second, String element) {
        switch (element) {
            case "+":
                return new AddInterpreter(first, second);
            case "-":
                return new SubInterpreter(first, second);
            case "*":
                return new MultiInterpreter(first, second);
            case "/":
                return new DivInterpreter(first, second);
            default:
                return null;
        }
    }
}
