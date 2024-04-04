package org.fufeng.design.pattern.creational.simplefactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author fufeng
 * {@code @Date} 2024-04-04 17:57
 */
public class VideoFactory {

    //v2
    public Video getVideo(String type) {
        if ("java".equalsIgnoreCase(type)) {
            return new JavaVideo();
        } else if ("python".equalsIgnoreCase(type)) {
            return new PythonVideo();
        } else {
            return null;
        }
    }

    //v3
    public Video getVideo(Class<?> c) {
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return video;
    }

}
