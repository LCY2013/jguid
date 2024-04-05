package org.fufeng.design.pattern.creational.prototype;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 17:10
 */
public class MailUtil {

    public static void sendMail(Mail mail) {
        String outputContent = "向" + mail.getName() + "发送邮件，邮件地址为：" + mail.getEmailAddress() + "邮件主题为：" + mail.getSubject() + "，邮件内容为：" + mail.getContent();
        System.out.println(outputContent + ":" + mail);
    }

    public static void saveOriginalMailRecord(Mail mail) {
        System.out.println("存储" + mail.getName() + "邮件记录："+mail.getContent() + ":" + mail);
    }

}
