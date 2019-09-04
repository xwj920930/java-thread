package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 公平锁和非公平锁
 * @Author yuki
 * @Date 2019/1/4 15:05
 * @Version 1.0
 **/
class MyService4{
    private Lock lock;
    MyService4(boolean isFair){
        lock=new ReentrantLock(isFair);
    }
    public void method(){
        try {
            lock.lock();
            System.out.println("获得锁定："+Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }
}
public class FairAndNotFair {
    public static void main(String[] args) {
        final MyService4 service4=new MyService4(false);
//        final MyService4 service4=new MyService4(true);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("运行了："+Thread.currentThread().getName());
                service4.method();
            }
        };
        Thread[] threads=new Thread[10];
        for (int i=0;i<10;i++)
            threads[i]=new Thread(runnable);
        for (int i=0;i<10;i++)
            threads[i].start();
    }
}
