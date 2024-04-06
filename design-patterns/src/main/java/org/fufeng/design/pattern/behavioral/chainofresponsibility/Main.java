package org.fufeng.design.pattern.behavioral.chainofresponsibility;

/**
 * {@link jakarta.servlet.Filter}
 * {@link jakarta.servlet.FilterChain}
 * {@link ch.qos.logback.classic.selector.servlet.LoggerContextFilter}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 18:02
 */
public class Main {

    public static void main(String[] args) {
        Approver articleApprover = new ArticleApprover();
        Approver videoApprover = new VideoApprover();

        Course course = new Course();
        course.setName("java设计模式精讲");
        course.setArticle("java设计模式精讲手记");
        course.setVideo("java设计模式精讲视频");
        articleApprover.setApprover(videoApprover);
        articleApprover.deploy(course);
    }

}
