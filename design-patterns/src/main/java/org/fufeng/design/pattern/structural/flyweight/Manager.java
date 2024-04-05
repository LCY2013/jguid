package org.fufeng.design.pattern.structural.flyweight;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 20:24
 */
public class Manager implements Employee {

    @Override
    public void report() {
        System.out.println("This is a report from " + department + ": " + reportContent);
    }

    private String department;
    private String reportContent;

    public Manager(String department) {
        this.department = department;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
}
