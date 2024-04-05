package org.fufeng.design.pattern.structural.bridge;

/**
 * 桥接模式
 * {@link java.sql.Driver}
 * {@link java.sql.DriverManager}
 * {@link java.sql.Connection}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 22:07
 */
public class Main {

    public static void main(String[] args) {
        Bank bank = new ICBCBank(new DepositAccount());
        Account account = bank.openAccount();
        account.showAccountType();

        bank = new ICBCBank(new SavingAccount());
        account = bank.openAccount();
        account.showAccountType();

        bank = new ABCBank(new SavingAccount());
        account = bank.openAccount();
        account.showAccountType();
    }

}
