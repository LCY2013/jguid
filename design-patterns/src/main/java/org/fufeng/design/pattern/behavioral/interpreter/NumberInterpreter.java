package org.fufeng.design.pattern.behavioral.interpreter;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 11:08
 */
public class NumberInterpreter implements Interpreter {
    private int number;

    public NumberInterpreter(int number) {
        this.number = number;
    }

    public NumberInterpreter(String number) {
        this.number = Integer.parseInt(number);
    }

    public int interpret() {
        return this.number;
    }
}
