package org.fufeng.design.pattern.behavioral.mediator;


import java.util.Date;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:37
 */
public class StudyGroup {

    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() + "【群消息】[" + user.getName() + "]：" + message);
    }

}
