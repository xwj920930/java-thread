package com.xwj.javaThreadProgramming.chapter6;

/**
 * @Description 懒汉模式
 * @Author yuki
 * @Date 2019/1/15 18:23
 * @Version 1.0
 **/
class MyObject3{
    private static class InnerClass{
        private static MyObject3 myObject3=new MyObject3();
    }
    public static MyObject3 getInstance(){
        return InnerClass.myObject3;
    }
}

class MyThread3 extends Thread{
    @Override
    public void run() {
        System.out.println(MyObject3.getInstance().hashCode());
    }
}

public class SingleModel3 {
    public static void main(String[] args) {
        MyThread3 thread1=new MyThread3();
        MyThread3 thread2=new MyThread3();
        MyThread3 thread3=new MyThread3();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
