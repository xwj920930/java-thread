package com.xwj.artOfConcurrency.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description CyclicBarrier测试，类似于join
 * 几个线程同时到达await后再一起执行
 * @Author yuki
 * @Date 2019/5/21 9:50
 * @Version 1.0
 **/
public class CyclicBarrierTest {
    static CyclicBarrier barrier=new CyclicBarrier(2);
    public static void main(String[] args) {
        new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }
            System.out.println(1);
        }).start();
        new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }
            System.out.println(2);
        }).start();
    }
}
