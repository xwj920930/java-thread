package com.xwj.javaThreadProgramming.chapter7;

/**
 * @Description 线程异常处理1.单独设置
 * @Author yuki
 * @Date 2019/1/21 18:43
 * @Version 1.0
 **/
class MyThread3 extends Thread{
    @Override
    public void run() {
        String s=null;
        System.out.println(s.hashCode());
    }
}
public class ThreadWithException1 {
    public static void main(String[] args) {
        MyThread3 myThread3=new MyThread3();
        myThread3.setName("t3");
        myThread3.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"出现异常");
            }
        });
        myThread3.start();
        MyThread3 myThread31=new MyThread3();
        myThread31.start();
    }
}
