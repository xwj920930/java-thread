package com.xwj.javaThreadProgramming.chapter6;

/**
 * @Description 饿汉模式
 * @Author yuki
 * @Date 2019/1/15 18:23
 * @Version 1.0
 **/
class MyObject1{
    private static MyObject1 myObject1=new MyObject1();
    public static MyObject1 getInstance(){
        return myObject1;
    }
}
class MyThread1 extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MyObject1.getInstance().hashCode());
    }
}
public class SingleModel1 {
    public static void main(String[] args) {
        MyThread1 thread1=new MyThread1();
        MyThread1 thread2=new MyThread1();
        MyThread1 thread3=new MyThread1();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
