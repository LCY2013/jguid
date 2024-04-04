package org.fufeng.design.pattern.creational.factorymethod;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 工厂方法模式
 * {@link java.util.Collection}
 * {@link Collection#iterator()}
 * {@link ArrayList.Itr}
 *
 * {@link URLStreamHandlerFactory}
 * {@link URLStreamHandler}
 * {@link sun.net.www.protocol.file.Handler#openConnection(URL)}
 * {@link URLConnection}
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:56
 */
public class VideoMain {

    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }

}
