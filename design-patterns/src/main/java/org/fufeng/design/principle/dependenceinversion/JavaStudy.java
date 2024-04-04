package org.fufeng.design.principle.dependenceinversion;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 16:02
 */
public class JavaStudy implements IStudy {

    @Override
    public void study() {
        System.out.println("user study java course");
    }

}
