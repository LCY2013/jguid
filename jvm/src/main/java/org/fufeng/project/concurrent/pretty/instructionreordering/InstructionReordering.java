package org.fufeng.project.concurrent.pretty.instructionreordering;

/**
 * @ClassName: InstructionReordering
 * @author: LuoChunYun
 * @Date: 2019/5/19 17:46
 * @Description: TODO
 *  指令重排序
 */
public class InstructionReordering {

    private static int num = 0;

    private static volatile boolean ready = false;

    public static void main(String[] args) {
        //启动读线程
        ReadThread readThread = new ReadThread();
        readThread.start();

        //启动写线程
        WriteThread writeThread = new WriteThread();
        writeThread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main exit()");
    }

    /**
     * 这里的结果可能存在多个情况一个是0，一个是4
     *  因为WriteThread哪里存在指令的重新排序，4可能在3执行，这个时候如果顺序为4、1、2、3，就会出现0
     *  所以防止这种指令重排序的情况出现，最好在ready上加上volatile，
     *   保证其之前的写操作不会排到该属性操作后面
     *   保证其之后的写操作不会排到该属性操作前面
     */
    public static class ReadThread extends Thread{

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                if(ready){ //1
                    System.out.println(num + num); //2
                    break;
                }
            }
        }
    }

    public static class WriteThread extends Thread{

        @Override
        public void run() {
            num = 2; //3
            ready = true;
            System.out.println("writeThread set over ...");
        }
    }

}
