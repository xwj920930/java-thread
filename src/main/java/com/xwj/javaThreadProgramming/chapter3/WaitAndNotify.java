package com.xwj.javaThreadProgramming.chapter3;

/**
 * @Description 测试wait和notify
 * @Author yuki
 * @Date 2018/12/29 9:55
 * @Version 1.0
 **/
class Thread1 extends Thread{
    private Object lock;
    Thread1(Object lock){
        this.lock=lock;
    }
    @Override
    public void run() {
        synchronized (lock){
            System.out.println("wait start:"+System.currentTimeMillis());
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait end:"+System.currentTimeMillis());
        }
    }
}
class Thread2 extends Thread{
    private Object lock;
    Thread2(Object lock){
        this.lock=lock;
    }
    @Override
    public void run() {
        synchronized (lock){
            System.out.println("notify start:"+System.currentTimeMillis());
            lock.notify();
            System.out.println("notify end:"+System.currentTimeMillis());
        }
    }
}
public class WaitAndNotify {
    public static void main(String[] args) {
        Object lock=new Object();
        Thread1 thread1=new Thread1(lock);
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread2 thread2=new Thread2(lock);
        thread2.start();
    }
}
