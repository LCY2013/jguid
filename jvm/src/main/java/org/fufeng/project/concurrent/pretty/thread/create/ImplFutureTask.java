package org.fufeng.project.concurrent.pretty.thread.create;

import java.util.concurrent.*;

/**
 * @ClassName: ImplFutureTask
 * @author: LuoChunYun
 * @Date: 2019/5/16 22:16
 * @Description: TODO
 */
public class ImplFutureTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "ImplFutureTask";
    }

    public static void main(String[] args) {
        //创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new ImplFutureTask());
        //执行线程任务
        new Thread(futureTask).start();
        try {
            //futureTask.get();
            final String result = futureTask.get(500, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
