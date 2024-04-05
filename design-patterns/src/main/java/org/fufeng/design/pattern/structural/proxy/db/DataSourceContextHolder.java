package org.fufeng.design.pattern.structural.proxy.db;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:38
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDB() {
        return contextHolder.get();
    }

    public static void clearDB() {
        contextHolder.remove();
    }

    public static void main(String[] args) {
        DataSourceContextHolder.setDB("db1");
        System.out.println(DataSourceContextHolder.getDB());
        DataSourceContextHolder.clearDB();
        System.out.println(DataSourceContextHolder.getDB());
    }
}
