package com.xwj.javaThreadProgramming.chapter3;

/**
 * @Description 生产者消费者模型
 * @Author yuki
 * @Date 2019/1/2 9:36
 * @Version 1.0
 **/
class Producer{
    private String lock;
    Producer(String lock){
        this.lock=lock;
    }public void setValue() throws InterruptedException {
        synchronized (lock){
            while (!ValueObject.value.equals("")){
                System.out.println("生产者:"+Thread.currentThread()+"等待");
                lock.wait();
            }
            System.out.println("生产者:"+Thread.currentThread()+"执行");
            ValueObject.value="A";
            lock.notifyAll();
        }
    }
}
class Consumer{
    private String lock;
    Consumer(String lock){
        this.lock=lock;
    }public void getValue() throws InterruptedException {
        synchronized (lock){
            while (ValueObject.value.equals("")){
                System.out.println("消费者:"+Thread.currentThread()+"等待");
                lock.wait();
            }
            System.out.println("消费者:"+Thread.currentThread()+"执行");
            ValueObject.value="";
            lock.notifyAll();
        }
    }
}
class ThreadP extends Thread{
    private Producer producer;
    ThreadP(Producer producer){
        this.producer=producer;
    }
    @Override
    public void run() {
        try {
            while (true){
                producer.setValue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadC extends Thread{
    private Consumer consumer;
    ThreadC(Consumer consumer){
        this.consumer=consumer;
    }
    @Override
    public void run() {
        try {
            while (true){
                consumer.getValue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ProducerAndConsumer {
    public static void main(String[] args) {
        String lock="asd";
        Producer producer=new Producer(lock);
        Consumer consumer=new Consumer(lock);
        ThreadP[] threadPS=new ThreadP[2];
        ThreadC[] threadCS=new ThreadC[2];
        for (int i=0;i<2;i++){
            threadPS[i]=new ThreadP(producer);
            threadCS[i]=new ThreadC(consumer);
            threadPS[i].start();
            threadCS[i].start();
        }
    }
}
