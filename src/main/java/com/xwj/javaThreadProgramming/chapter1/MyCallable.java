package com.xwj.javaThreadProgramming.chapter1;

import java.util.concurrent.*;

/**
 * @Description callable类似Runnable，区别是有返回值，可以抛异常
 * @Author yuki
 * @Date 2019/4/29 9:38
 * @Version 1.0
 **/
public class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "执行完毕";
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable+futureTask+thread实现，futureTask获取返回数据
        MyCallable myCallable=new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        //futureTask最终继承了runnable，所以可以用thread启动
        new Thread(futureTask).start();
        System.out.println("主线程干别的");
        //阻塞,相当于用来join方法
        String s = futureTask.get();
        System.out.println("获取计算结果"+s);
        //callable+futureTask+pool实现，futureTask获取返回数据
        ExecutorService pool = Executors.newCachedThreadPool();
//        pool.submit(futureTask);
//        pool.shutdown();
//        System.out.println("主线程干别的");
//        String s = futureTask.get();
//        System.out.println("获取计算结果"+s);
        //callable+future+pool实现，future获取返回数据
//        Future<String> future = pool.submit(myCallable);
//        pool.shutdown();
//        System.out.println("主线程干别的");
//        String s = future.get();
//        System.out.println("获取计算结果"+s);
    }
}
