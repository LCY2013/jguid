package org.fufeng.design.pattern.structural.bridge;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:04
 */
public class ICBCBank extends Bank{

    public ICBCBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("打开中国工商银行账号");
        account.openAccount();
        return this.account;
    }

}
