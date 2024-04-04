package org.fufeng.design.pattern.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 16:48
 */
public class BirdMain {

    public static void main(String[] args) {
        //v1
        /*Bird bird = new Bird();
        bird.mainMoveMode("大雁");
        bird.mainMoveMode("鸵鸟");*/

        //v2
        FlyBird flyBird = new FlyBird();
        flyBird.mainMoveMode("大雁");
        WalkBird walkBird = new WalkBird();
        walkBird.mainMoveMode("鸵鸟");
    }

}
