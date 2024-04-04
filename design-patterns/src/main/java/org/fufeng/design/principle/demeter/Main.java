package org.fufeng.design.principle.demeter;

/**
 * 迪米特原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:35
 */
public class Main {

    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }

}
