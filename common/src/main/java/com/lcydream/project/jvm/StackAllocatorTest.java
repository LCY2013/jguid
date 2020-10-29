package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: 逃逸分析与栈上分配
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 10:17
 */
public class StackAllocatorTest {

    private StackAllocatorTest sat;

    //逃逸
    private StackAllocatorTest getInstance(){
        return this.sat == null?new StackAllocatorTest():this.sat;
    }

    //逃逸
    private void setSat(StackAllocatorTest sat){
        this.sat = sat;
    }

    //没有逃逸，栈上分配(经过逃逸分析，对于哪些没有逃逸的对象，在栈上进行分配)
    private void useSat(){
        StackAllocatorTest sat = new StackAllocatorTest();
    }
}
