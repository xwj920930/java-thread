package com.xwj.artOfConcurrency.chapter6;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description 阻塞队列用于生产者消费者模型实例
 * @Author yuki
 * @Date 2019/5/17 10:01
 * @Version 1.0
 **/
public class BlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(10);
        new producer(integers).start();
        new Consumer(integers).start();
    }
}
class Consumer extends Thread{
    private ArrayBlockingQueue<Integer> queue;
    Consumer(ArrayBlockingQueue<Integer> queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        while (true){
            try {
                Integer take = queue.take();
                System.out.println("消费者取出："+take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class producer extends Thread{
    private ArrayBlockingQueue<Integer> queue;
    producer(ArrayBlockingQueue<Integer> queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                queue.put(i);
                System.out.println("生产者塞入："+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
