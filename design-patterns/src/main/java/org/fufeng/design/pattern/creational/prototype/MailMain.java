package org.fufeng.design.pattern.creational.prototype;

import java.util.ArrayList;

/**
 * 原型模式
 * jdk clone
 * {@link ArrayList}
 * {@link java.util.HashMap}
 *
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:12
 */
public class MailMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("init");
        System.out.println(mail);

        for (int i = 0; i < 10; i++) {
            Mail mailTemp = (Mail)mail.clone();
            mailTemp.setName("name"+i);
            mailTemp.setEmailAddress("emailAddress"+i+"@email.com");
            mailTemp.setContent("study");
            mailTemp.setSubject("subject"+i);
            MailUtil.sendMail(mailTemp);
        }
        MailUtil.saveOriginalMailRecord(mail);
    }

}
