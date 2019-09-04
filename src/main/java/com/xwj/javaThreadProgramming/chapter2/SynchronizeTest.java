package com.xwj.javaThreadProgramming.chapter2;

/**
 * @Description
 * synchronize锁对象而不是方法
 * 如果是锁对象：对象只有一把锁，访问方法A的时候B也无法进入
 * 如果是锁方法：方法各自有锁，访问方法A的时候B不受影响
 * @Author yuki
 * @Date 2018/12/25 15:49
 * @Version 1.0
 **/
class MyObject{
    synchronized public void methodA() throws InterruptedException {
        System.out.println("begin methodA threadName= "+Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("end methodA");
    }
    synchronized public void methodB() throws InterruptedException {
        System.out.println("begin methodB threadName= "+Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("end methodB");
    }
}
class ThreadA extends Thread{
    private MyObject myObject;
    ThreadA(MyObject myObject){
        this.myObject=myObject;
    }
    @Override
    public void run() {
        try {
            myObject.methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadB extends Thread{
    private MyObject myObject;
    ThreadB(MyObject myObject){
        this.myObject=myObject;
    }
    @Override
    public void run() {
        try {
            myObject.methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class SynchronizeTest {
    public static void main(String[] args) {
        MyObject myObject=new MyObject();
        ThreadA a=new ThreadA(myObject);
        a.start();
        ThreadB b=new ThreadB(myObject);
        b.start();
    }
}
