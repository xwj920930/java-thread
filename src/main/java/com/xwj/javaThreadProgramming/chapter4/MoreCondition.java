package com.xwj.javaThreadProgramming.chapter4;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description lock+多condition实现部分通知
 * @Author yuki
 * @Date 2019/1/4 14:49
 * @Version 1.0
 **/
class MyService2{
    private Lock lock=new ReentrantLock();
    private Condition conditionA=lock.newCondition();
    private Condition conditionB=lock.newCondition();
    public void awaitA(){
        try {
            lock.lock();
            System.out.println("awaitA时间为:"+System.currentTimeMillis());
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void awaitB(){
        try {
            lock.lock();
            System.out.println("awaitB时间为:"+System.currentTimeMillis());
            conditionB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signal_A(){
        try {
            lock.lock();
            System.out.println("signal_A时间为:"+System.currentTimeMillis());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }
    public void signal_B(){
        try {
            lock.lock();
            System.out.println("signal_B时间为:"+System.currentTimeMillis());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
class ThreadA2 extends Thread{
    private MyService2 myService2;
    ThreadA2(MyService2 myService2){
        this.myService2=myService2;
    }
    @Override
    public void run() {
        myService2.awaitA();
    }
}
class ThreadB2 extends Thread{
    private MyService2 myService2;
    ThreadB2(MyService2 myService2){
        this.myService2=myService2;
    }
    @Override
    public void run() {
        myService2.awaitB();
    }
}
public class MoreCondition {
    public static void main(String[] args) throws InterruptedException {
        MyService2 myService2=new MyService2();
        ThreadA2 threadA2=new ThreadA2(myService2);
        threadA2.start();
        ThreadB2 threadB2=new ThreadB2(myService2);
        threadB2.start();
        Thread.sleep(3000);
        myService2.signal_A();
    }
}
