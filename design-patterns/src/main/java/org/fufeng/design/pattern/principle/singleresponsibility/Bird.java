package org.fufeng.design.pattern.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 16:47
 */
public class Bird {

    public void mainMoveMode(String birdName)
    {
        if("鸵鸟".equals(birdName))
        {
            System.out.println(birdName + "用脚走");
        }
        else
        {
            System.out.println(birdName + "用翅膀飞");
        }
    }

}
