package org.fufeng.design.pattern.creational.prototype;

/**
 * 原型模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:09
 */
public class Mail implements Cloneable {
    private String name;
    private String emailAddress;
    private String content;
    private String subject;

    public Mail() {
        System.out.println("Mail()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                '}' + super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("clone mail");
        return super.clone();
    }
}
