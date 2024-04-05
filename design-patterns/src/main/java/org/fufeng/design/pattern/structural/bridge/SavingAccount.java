package org.fufeng.design.pattern.structural.bridge;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 21:59
 */
public class SavingAccount implements Account {
    @Override
    public Account openAccount() {
        System.out.println("打开活期账号");
        return new SavingAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("这是一个活期账号");
    }
}
