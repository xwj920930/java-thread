package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description condition模拟生产者消费者
 * @Author yuki
 * @Date 2019/1/4 15:05
 * @Version 1.0
 **/
class MyService3{
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private boolean hasValue=false;
    public void set(){
        try {
            lock.lock();
            while (hasValue==true){
                condition.await();
            }
            hasValue=true;
            condition.signalAll();
            System.out.println("+++++"+Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void get(){
        try {
            lock.lock();
            while (hasValue==false){
                condition.await();
            }
            hasValue=false;
            condition.signalAll();
            System.out.println("-----"+Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class ThreadA3 extends Thread{
    private MyService3 myService3;
    ThreadA3(MyService3 myService3){
        this.myService3=myService3;
    }
    @Override
    public void run() {
        for (int i=0;i<Integer.MAX_VALUE;i++)
        myService3.set();
    }
}
class ThreadB3 extends Thread{
    private MyService3 myService3;
    ThreadB3(MyService3 myService3){
        this.myService3=myService3;
    }
    @Override
    public void run() {
        for (int i=0;i<Integer.MAX_VALUE;i++)
        myService3.get();
    }
}
public class ProducerAndConsumer {
    public static void main(String[] args) {
        MyService3 service3=new MyService3();
        for (int i=0;i<3;i++){
            new ThreadA3(service3).start();
            new ThreadB3(service3).start();
        }
    }
}
