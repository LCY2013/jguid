package org.fufeng.design.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 20:25
 */
public class EmployeeFactory {
    private final static Map<String, Employee> EMPLOYEES = new HashMap<>();

    public static Employee getEmployee(String department) {
        if (!EMPLOYEES.containsKey(department)) {
            Manager manager = new Manager(department);
            EMPLOYEES.put(department, manager);
            System.out.println("new manager created for " + department);
            String reportContent = "report content for " + department + ", ....";
            manager.setReportContent(reportContent);
            System.out.println("report content: "+ reportContent);
            return manager;
        }
        return EMPLOYEES.get(department);
    }
}
