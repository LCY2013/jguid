package org.fufeng.project.concurrent.pretty.thread.producer_consumer;

import java.util.concurrent.*;

/**
 * @ClassName: WaitNotifyAll
 * @author: LuoChunYun
 * @Date: 2019/5/17 21:12
 * @Description: 生产者:消费者 N:1
 */
public class WaitNotifyAllManyToOne {

    private final static int MAX_SIZE = 5;

    private final static Object object = new Object();

    public static void main(String[] args) {
        //阻塞队列
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(MAX_SIZE);
        //创建线程池
        final ExecutorService executorService = Executors.newCachedThreadPool();
        //提交生产者One任务
        executorService.submit(new ProducerTask(linkedBlockingQueue,"producerOne"));
        //提交生产者Two任务
        executorService.submit(new ProducerTask(linkedBlockingQueue,"producerTwo"));
        //提交消费者任务
        executorService.submit(new ConsumerTask(linkedBlockingQueue,"consumerOne"));

        ScheduledThreadPoolExecutor scheduledExecutorService =
                new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.schedule(()-> System.exit(0),2000,TimeUnit.MILLISECONDS);

        executorService.shutdown();
    }



    public static class ProducerTask implements Runnable{

        private final LinkedBlockingQueue<String> linkedBlockingQueue;

        private static int count = 0;

        private final String name;

        public ProducerTask(LinkedBlockingQueue<String> linkedBlockingQueue,String name) {
            this.linkedBlockingQueue = linkedBlockingQueue;
            this.name = name;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()){
                synchronized (linkedBlockingQueue) {
                    if (linkedBlockingQueue.size() == MAX_SIZE) {
                        try {
                            System.out.println(name+":wait");
                            linkedBlockingQueue.notifyAll();
                            linkedBlockingQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        String prodcer = name+":" + (++count);
                        System.out.println(prodcer);
                        linkedBlockingQueue.add(prodcer);
                    }
                }
                Thread.yield();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ConsumerTask implements Runnable{

        private final LinkedBlockingQueue<String> linkedBlockingQueue;

        private final String name;

        public ConsumerTask(LinkedBlockingQueue<String> linkedBlockingQueue,String name) {
            this.linkedBlockingQueue = linkedBlockingQueue;
            this.name = name;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()){
                synchronized (linkedBlockingQueue) {
                    if (linkedBlockingQueue.size() == 0) {
                        try {
                            System.out.println(name+":wait");
                            linkedBlockingQueue.notifyAll();
                            linkedBlockingQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println(name+":"+linkedBlockingQueue.poll());
                    }
                }
                //申请线程切换，体现多个消费者的效果
                Thread.yield();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
