package org.fufeng.project.concurrent.pretty.thread.producer_consumer;

import java.util.concurrent.*;

/**
 * @ClassName: WaitNotifyAll
 * @author: LuoChunYun
 * @Date: 2019/5/17 21:12
 * @Description: 生产者:消费者 1:1
 */
public class WaitNotifyAllOneToOne {

    private final static int MAX_SIZE = 5;

    private final static Object object = new Object();

    public static void main(String[] args) {
        //阻塞队列
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(MAX_SIZE);
        //创建线程池
        final ExecutorService executorService = Executors.newCachedThreadPool();
        //提交生产者任务
        executorService.submit(new ProducerTask(linkedBlockingQueue));
        //提交第一个消费者任务
        executorService.submit(new ConsumerTask(linkedBlockingQueue));


        ScheduledThreadPoolExecutor scheduledExecutorService =
                new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.schedule(()-> System.exit(0),3,TimeUnit.MILLISECONDS);

        executorService.shutdown();
    }



    public static class ProducerTask implements Runnable{

        private final LinkedBlockingQueue<String> linkedBlockingQueue;

        private static int count = 0;

        public ProducerTask(LinkedBlockingQueue<String> linkedBlockingQueue) {
            this.linkedBlockingQueue = linkedBlockingQueue;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()){
                synchronized (linkedBlockingQueue) {
                    if (linkedBlockingQueue.size() == MAX_SIZE) {
                        try {
                            System.out.println("producer:wait");
                            linkedBlockingQueue.notifyAll();
                            linkedBlockingQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        String prodcer = "producer:" + (++count);
                        System.out.println(prodcer);
                        linkedBlockingQueue.add(prodcer);
                    }
                }
            }
        }
    }

    public static class ConsumerTask implements Runnable{

        private final LinkedBlockingQueue<String> linkedBlockingQueue;

        public ConsumerTask(LinkedBlockingQueue<String> linkedBlockingQueue) {
            this.linkedBlockingQueue = linkedBlockingQueue;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()){
                synchronized (linkedBlockingQueue) {
                    if (linkedBlockingQueue.size() == 0) {
                        try {
                            System.out.println("consumer:wait");
                            linkedBlockingQueue.notifyAll();
                            linkedBlockingQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("consumer:"+linkedBlockingQueue.poll());
                    }
                }
            }
        }
    }
}
