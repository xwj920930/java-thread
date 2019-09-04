package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 继承thread
 * @Author yuki
 * @Date 2018/12/24 16:23
 * @Version 1.0
 **/
public class MyThread extends Thread{
    private int i;
    MyThread(int i){
        this.i=i;
    }
    @Override
    public void run() {
        System.out.println("my thread"+i);
    }

    public static void main(String[] args) {
        MyThread thread1=new MyThread(1);
        MyThread thread2=new MyThread(2);
        MyThread thread3=new MyThread(3);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
