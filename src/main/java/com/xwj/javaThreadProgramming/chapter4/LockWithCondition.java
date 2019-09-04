package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 在lock中使用condition
 * @Author yuki
 * @Date 2019/1/3 15:27
 * @Version 1.0
 **/
class MyService1{
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void await(){
        try {
            lock.lock();
            System.out.println("await时间为:"+System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signal(){
        try {
            lock.lock();
            System.out.println("signal时间为:"+System.currentTimeMillis());
            condition.signal();
//            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
class ThreadA1 extends Thread{
    private MyService1 myService1;
    ThreadA1(MyService1 myService1){
        this.myService1=myService1;
    }
    @Override
    public void run() {
        myService1.await();
    }
}
public class LockWithCondition {
    public static void main(String[] args) throws InterruptedException {
        MyService1 myService1=new MyService1();
        ThreadA1 threadA1=new ThreadA1(myService1);
        threadA1.start();
        Thread.sleep(3000);
        myService1.signal();
    }
}
