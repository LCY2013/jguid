module modular {
    // requires 内部依赖
    // 命名模块
    // artifact 资源根 package 存有 module-info.class（包含模块名称）
    requires java.base; // 默认依赖
    requires java.sql; // 依赖传递 requires transitive
    requires java.compiler; // exports 控制可访问的 API 包

    // 非命名模块
    requires transitive spring.context;
    requires transitive commons.lang;  // 规律：
    // Maven artifactId（jar或者war文件）中横划（画）线 "-"
    // 在模块化名称使用 "."
    requires transitive commons.collections;

    // exports 供外部访问
    exports org.fufeng.modular;
}