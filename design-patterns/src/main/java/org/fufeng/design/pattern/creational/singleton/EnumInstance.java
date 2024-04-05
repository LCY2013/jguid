package org.fufeng.design.pattern.creational.singleton;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 16:07
 */
public enum EnumInstance {
    INSTANCE {
        protected void printTest() {
            System.out.println("Print Test");
        }
    };
    protected abstract void printTest();

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static EnumInstance getInstance() {
        return INSTANCE;
    }

}
