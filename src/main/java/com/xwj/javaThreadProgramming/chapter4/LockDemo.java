package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 测试lock
 * @Author yuki
 * @Date 2019/1/3 14:42
 * @Version 1.0
 **/
class MyService{
    private Lock lock=new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i=0;i<5;i++){
            System.out.println("thread:"+Thread.currentThread().getName()+":"+(i+1));
        }
        lock.unlock();
    }
}
class MyThread extends Thread{
    private MyService myService;
    MyThread(MyService myService){
        this.myService=myService;
    }
    @Override
    public void run() {
        myService.testMethod();
    }
}
public class LockDemo {
    public static void main(String[] args) {
        MyService myService=new MyService();
        new MyThread(myService).start();
        new MyThread(myService).start();
        new MyThread(myService).start();
        new MyThread(myService).start();
        new MyThread(myService).start();
    }
}
