package org.fufeng.design.pattern.behavioral.chainofresponsibility;

import java.util.Objects;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:58
 */
public class ArticleApprover extends Approver {

    @Override
    public void deploy(Course course) {
        if (Objects.nonNull(course.getArticle())) {
            System.out.println(course.getName() + "含有手记，批准");
            if (approver != null) {
                approver.deploy(course);
            }
        } else {
            System.out.println(course.getName() + "不含有手记，不批准");
        }
    }

}
