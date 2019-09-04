package com.xwj.javaThreadProgramming.chapter7;

/**
 * @Description 一级关联线程组
 * @Author yuki
 * @Date 2019/1/17 19:04
 * @Version 1.0
 **/
class MyThread1 extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("threadName="+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadGroup1 {
    public static void main(String[] args) {
        MyThread1 thread1=new MyThread1();
        MyThread1 thread2=new MyThread1();
        ThreadGroup group=new ThreadGroup("threadGroup");
        Thread t1=new Thread(group,thread1);
        Thread t2=new Thread(group,thread2);
        t1.start();
        t2.start();
        System.out.println("threadGroupCount:"+group.activeCount());
        System.out.println("threadGroupName:"+group.getName());
    }
}
