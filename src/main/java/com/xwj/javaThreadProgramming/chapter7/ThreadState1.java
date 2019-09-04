package com.xwj.javaThreadProgramming.chapter7;

/**
 * @Description new：线程实例化后还未start，甚至在构造器之前
runnable：线程执行，包含构造器和run方法
terminated：线程销毁
 * @Author yuki
 * @Date 2019/1/17 10:29
 * @Version 1.0
 **/
public class ThreadState1 extends Thread {
    ThreadState1(){
        System.out.println("构造方法:"+Thread.currentThread().getState());
    }
    @Override
    public void run() {
        System.out.println("run方法:"+Thread.currentThread().getState());
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadState1 state1=new ThreadState1();
        System.out.println("main1:"+state1.getState());
        Thread.sleep(1000);
        state1.start();
        Thread.sleep(1000);
        System.out.println("main2:"+state1.getState());
    }
}
