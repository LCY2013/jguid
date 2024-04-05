package org.fufeng.design.pattern.structural.bridge;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:02
 */
public class ABCBank extends Bank {

    public ABCBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("打开中国农业银行账号");
        account.openAccount();
        return this.account;
    }

}
