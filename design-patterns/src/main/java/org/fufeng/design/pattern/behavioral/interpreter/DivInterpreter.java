package org.fufeng.design.pattern.behavioral.interpreter;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 11:07
 */
public class DivInterpreter implements  Interpreter{
    private Interpreter firstExpression, secondExpression;

    public DivInterpreter(Interpreter firstExpression, Interpreter secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public int interpret() {
        return this.firstExpression.interpret() / this.secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "/";
    }
}
