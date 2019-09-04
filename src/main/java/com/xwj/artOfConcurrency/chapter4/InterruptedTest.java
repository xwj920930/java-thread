package com.xwj.artOfConcurrency.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @Description 测试sleep状态下的interrupt
 * sleep、wait、notify、join，这些方法遇到中断会抛出InterruptedException
 * @Author yuki
 * @Date 2019/2/21 14:51
 * @Version 1.0
 **/
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread=new Thread(new SleepRunner(),"sleepThread");
        //设置为守护线程，main停止时立即停止
        sleepThread.setDaemon(true);
        Thread busyThread=new Thread(new BusyRunner(),"busyThread");
        //设置为守护线程，main停止时立即停止
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        System.out.println("sleepThread interrupt is "+sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is "+busyThread.isInterrupted());
        TimeUnit.SECONDS.sleep(5);
        //设置停止标志
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupt is "+sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is "+busyThread.isInterrupted());
    }
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){
            }
        }
    }
}
