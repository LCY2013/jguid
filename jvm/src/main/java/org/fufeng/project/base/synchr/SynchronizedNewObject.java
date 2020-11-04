package org.fufeng.project.base.synchr;

/**
 * @ClassName: SynchronizedNewObject
 * @author: LuoChunYun
 * @Date: 2019/5/22 11:37
 * @Description: TODO
 */
public class SynchronizedNewObject {

    private int i = 0;

    public void add(){
        synchronized (new Object()){
            i++;
        }
    }
}
