package com.xwj.artOfConcurrency.chapter9;

import java.util.concurrent.*;

/**
 * @Description execute
 * threadPoolExecutor继承自executorService
 * @Author yuki
 * @Date 2019/5/21 17:09
 * @Version 1.0
 **/
class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("MyRunnable");
    }
}
public class ThreadPoolTest1 {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        pool.execute(new MyRunnable());
        pool.shutdown();
    }
}
