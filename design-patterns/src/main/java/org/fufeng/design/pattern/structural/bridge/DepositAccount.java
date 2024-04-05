package org.fufeng.design.pattern.structural.bridge;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 21:58
 */
public class DepositAccount implements Account {

    @Override
    public Account openAccount() {
        System.out.println("打开定期账号");
        return new DepositAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("这是一个定期账号");
    }

}
