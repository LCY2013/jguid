package org.fufeng.design.pattern.behavioral.templatemethod;

/**
 * 模版方法模式
 * @author fufeng
 * {@code @Date} 2024-04-05 23:26
 */
public abstract class ACourse {

    protected final void makeCourse() {
        this.makePPT();
        this.makeVideo();
        if (needWriteArticle()) {
            this.makeArticle();
        }
        this.packageCourse();
    }

    final protected void makePPT() {
        System.out.println("制作PPT");
    }

    final protected void makeVideo() {
        System.out.println("制作视频");
    }

    final protected void makeArticle() {
        System.out.println("制作文章");
    }

    //钩子方法
    protected boolean needWriteArticle() {
        return false;
    }

    abstract void packageCourse();
}
