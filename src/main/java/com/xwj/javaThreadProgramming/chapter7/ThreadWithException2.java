package com.xwj.javaThreadProgramming.chapter7;

/**
 * @Description 线程异常处理2.通用设置
 * @Author yuki
 * @Date 2019/1/21 18:43
 * @Version 1.0
 **/
public class ThreadWithException2 {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"出现异常");
            }
        });
        MyThread3 myThread3=new MyThread3();
        myThread3.setName("t3");
        myThread3.start();
        MyThread3 myThread31=new MyThread3();
        myThread31.start();
    }
}
