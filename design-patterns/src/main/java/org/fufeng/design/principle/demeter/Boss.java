package org.fufeng.design.principle.demeter;

/**
 * 迪米特法则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:31
 */
public class Boss {

    public void commandCheckNumber(TeamLeader teamLeader) {
        /*v1
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        teamLeader.checkNumberOfCourses(courseList);
         */

        //v2
        teamLeader.checkNumberOfCourses();
    }

}
