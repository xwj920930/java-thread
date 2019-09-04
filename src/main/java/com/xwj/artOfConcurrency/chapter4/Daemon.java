package com.xwj.artOfConcurrency.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @Description 守护线程
 * main线程不是守护线程
 * @Author yuki
 * @Date 2019/2/20 21:10
 * @Version 1.0
 **/
public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
//        TimeUnit.SECONDS.sleep(2);
    }
    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("finally");
            }
        }
    }
}
