package com.xwj.artOfConcurrency.chapter9;

import java.util.concurrent.*;

/**
 * @Description execute
 * threadPoolExecutor继承自executorService
 * @Author yuki
 * @Date 2019/5/21 17:09
 * @Version 1.0
 **/
class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "MyCallable";
    }
}
public class ThreadPoolTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        Future<String> submit = pool.submit(new MyCallable());
        System.out.println(submit.get());
        pool.shutdown();
    }
}
