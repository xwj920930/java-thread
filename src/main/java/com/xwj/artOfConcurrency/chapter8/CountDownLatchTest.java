package com.xwj.artOfConcurrency.chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * @Description CountDownLatch测试
 * 功能类似于join
 * 几个线程都执行完成以后(countDown)再执行主线程(await)
 * @Author yuki
 * @Date 2019/5/21 9:35
 * @Version 1.0
 **/
public class CountDownLatchTest {
    private static CountDownLatch latch=new CountDownLatch(2);//定义两个量
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(1);
           latch.countDown();//减一
        }).start();
        new Thread(() -> {
            System.out.println(2);
            latch.countDown();//减一
        }).start();
        latch.await();//减到0时执行
        System.out.println(3);
    }
}
