package org.fufeng.design.pattern.structural.bridge;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:00
 */
public abstract class Bank {

    protected Account account;

    public Bank(Account account) {
        this.account = account;
    }

    abstract Account openAccount();

}
