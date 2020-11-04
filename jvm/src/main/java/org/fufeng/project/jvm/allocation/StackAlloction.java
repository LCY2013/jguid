package org.fufeng.project.jvm.allocation;

/**
 * @ClassName: StackAlloction
 * @author: LuoChunYun
 * @Date: 2019/5/17 16:42
 * @Description: TODO
 *  逃逸分析与栈上分配
 */
public class StackAlloction {

    private StackAlloction stackAlloction;

    /**
     * 发生逃逸不会进行栈上分配
     * @return void
     */
    public StackAlloction getStackAlloction() {
        return stackAlloction;
    }

    /**
     * 发生逃逸不会进行栈上分配
     * @param stackAlloction 栈上分配对象
     */
    public void setStackAlloction(StackAlloction stackAlloction) {
        this.stackAlloction = stackAlloction;
    }

    /**
     * 不会发生逃逸会进行栈上分配
     */
    public void useStackAlloction(){
        StackAlloction stackAlloction = new StackAlloction();
    }

    public static void main(String[] args) {
        System.out.println(1<<3);
        System.out.println(2<<3);
        System.out.println(3<<3);
        System.out.println(""+1+1);
    }
}
