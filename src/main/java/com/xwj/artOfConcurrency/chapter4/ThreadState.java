package com.xwj.artOfConcurrency.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程状态
 * @Author yuki
 * @Date 2019/2/20 19:33
 * @Version 1.0
 **/
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting").start();
        //类锁，第一个获取以后不释放锁第二个阻塞
        new Thread(new Blocked(),"Blocked1").start();
        new Thread(new Blocked(),"Blocked2").start();
    }
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    //sleep属于time_waiting(还包括wait(long))
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Blocked implements Runnable{
        @Override
        public void run() {
            //类锁，第一个获取锁不释放第二个阻塞
            synchronized (Blocked.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
