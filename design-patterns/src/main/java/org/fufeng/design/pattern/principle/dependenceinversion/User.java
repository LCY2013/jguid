package org.fufeng.design.pattern.principle.dependenceinversion;

/**
 * 依赖倒置
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 11:36
 */
public class User {

    // v1
    public void studyJavaCourse() {
        System.out.println("user study java course");
    }

    public void studyGoCourse() {
        System.out.println("user study go course");
    }

    public void studyPythonCourse() {
        System.out.println("user study python course");
    }


    //v2
    /*public void study(IStudy iStudy) {
        iStudy.study();
    }*/

    //v3
    /*private IStudy iStudy;

    public User(IStudy iStudy) {
        this.iStudy = iStudy;
    }

    public void study() {
        iStudy.study();
    }*/

    //v4
    private IStudy iStudy;

    public void setIStudy(IStudy iStudy) {
        this.iStudy = iStudy;
    }

    public void study() {
        iStudy.study();
    }

}
