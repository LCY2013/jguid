module lesson{
    requires java.base; // 默认依赖 java.base
    requires java.sql;  // 依赖 SQL（JDBC）
    //requires java.logging;
    requires java.desktop;
    exports org.fufeng.module.java.info;
    exports org.fufeng.module.java;
}