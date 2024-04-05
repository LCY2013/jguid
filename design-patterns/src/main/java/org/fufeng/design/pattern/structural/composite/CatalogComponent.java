package org.fufeng.design.pattern.structural.composite;

/**
 * 组合模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 20:49
 */
public abstract class CatalogComponent {

    public void add(CatalogComponent catalogComponent) throws Exception {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(CatalogComponent catalogComponent) throws Exception {
        throw new UnsupportedOperationException("不支持删除操作");
    }

    public String getName(CatalogComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持获取名称操作");
    }

    public double getPrice(CatalogComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持获取价格操作");
    }

    public void print() {
        throw new UnsupportedOperationException("不支持打印操作");
    }

}
