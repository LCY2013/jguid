package org.fufeng.design.pattern.structural.adapter.objectadapter;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:44
 */
public class Adapter implements Target {

    private final Adapt adapt = new Adapt();

    @Override
    public void request() {
        //do ...
        this.adapt.adapterRequest();
        //do ...
    }

}
