package org.fufeng.design.pattern.behavioral.chainofresponsibility;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:56
 */
public class Course {
    private String name;
    private String video;
    private String article;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", video='" + video + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
