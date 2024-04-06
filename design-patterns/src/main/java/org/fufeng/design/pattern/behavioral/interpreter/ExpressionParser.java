package org.fufeng.design.pattern.behavioral.interpreter;

import java.util.Stack;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 11:09
 */
public class ExpressionParser {
    private Stack<Interpreter> interpreterStack = new Stack<>();

    public int parse(String expression) {
        String[] elements = expression.split(" ");
        for (String element : elements) {
            switch (element) {
                case "+", "-", "*", "/" -> {
                    Interpreter expressionObject = OperatorUtil.getExpressionObject(interpreterStack.pop(), interpreterStack.pop(), element);
                    if (expressionObject != null) {
                        int interpret = expressionObject.interpret();
                        interpreterStack.push(new NumberInterpreter(interpret));
                    }
                }
                default -> interpreterStack.push(new NumberInterpreter(element));
            }
        }
        return interpreterStack.pop().interpret();
    }
}
