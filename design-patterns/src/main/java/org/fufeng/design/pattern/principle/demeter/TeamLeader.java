package org.fufeng.design.pattern.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:33
 */
public class TeamLeader {

    //v1
    /*public void checkNumberOfCourses(List<Course> courses) {
        System.out.println("TeamLeader.checkNumberOfCourses: "+courses.size());
    }*/

    //v2
    public void checkNumberOfCourses() {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courses.add(new Course());
        }
        System.out.println("TeamLeader.checkNumberOfCourses: "+courses.size());
    }

}
