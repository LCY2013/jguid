package org.fufeng.design.pattern.structural.adapter.classadapter;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:44
 */
public class Adapter extends Adapt implements Target {

    @Override
    public void request() {
        //do ...
        super.adapterRequest();
        //do ...
    }

}
