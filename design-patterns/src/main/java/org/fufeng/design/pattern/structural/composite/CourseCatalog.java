package org.fufeng.design.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 20:53
 */
public class CourseCatalog extends  CatalogComponent {
    private List<CatalogComponent> items = new ArrayList<>();
    private String name;
    private Integer level;

    public CourseCatalog(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(CatalogComponent catalogComponent) throws Exception {
        this.items.add(catalogComponent);
    }

    @Override
    public void remove(CatalogComponent catalogComponent) throws Exception {
        this.items.remove(catalogComponent);
    }

    @Override
    public void print() {
        System.out.println(this.name);
        this.items.forEach(item -> {
            for (int i =0; i < this.level; i++ ) {
                System.out.print(" ");
            }
            item.print();
        });
    }
}
