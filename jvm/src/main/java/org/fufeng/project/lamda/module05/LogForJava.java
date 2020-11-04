package org.fufeng.project.lamda.module05;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: LogForJava
 * @author: LuoChunYun
 * @Date: 2019/4/29 15:40
 * @Description: TODO
 */
public class LogForJava {

    private static Logger logger;

    static {
        logger = Logger.getLogger(LogForJava.class.getName());
        logger.setLevel(Level.ALL);
    }

    public static void main(String[] args) {
        //001 日志打印
        logInfo();
    }

    private static void logInfo() {
        int x = 2 ,y = 1;
        logger.finest("X:"+x+",Y:"+y);
        logger.finest(()->"X:"+x+",Y:"+y);
        logger.info("111");
    }
}
