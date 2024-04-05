package org.fufeng.design.pattern.behavioral.templatemethod;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 23:34
 */
public class FECourse extends ACourse {
    private boolean needWriteArticleFlag = false;

    public FECourse(boolean needWriteArticleFlag) {
        this.needWriteArticleFlag = needWriteArticleFlag;
    }

    @Override
    void packageCourse() {
        System.out.println("提供前端课程资料");
        System.out.println("提供前端课程视频");
    }

    @Override
    protected boolean needWriteArticle() {
        return this.needWriteArticleFlag;
    }
}
