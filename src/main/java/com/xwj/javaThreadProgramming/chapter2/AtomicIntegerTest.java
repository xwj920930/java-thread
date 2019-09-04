package com.xwj.javaThreadProgramming.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 原子操作具有同步性
 * @Author yuki
 * @Date 2018/12/28 11:09
 * @Version 1.0
 **/
public class AtomicIntegerTest extends Thread {
    private AtomicInteger count=new AtomicInteger(5);
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count.decrementAndGet());
    }
    public static void main(String[] args) {
        AtomicIntegerTest atomicIntegerTest=new AtomicIntegerTest();
        Thread thread1=new Thread(atomicIntegerTest);
        Thread thread2=new Thread(atomicIntegerTest);
        Thread thread3=new Thread(atomicIntegerTest);
        Thread thread4=new Thread(atomicIntegerTest);
        Thread thread5=new Thread(atomicIntegerTest);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
