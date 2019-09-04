package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 实现runnable
 * @Author yuki
 * @Date 2018/12/24 16:26
 * @Version 1.0
 **/
public class MyRunable implements Runnable{
    private int i;
    MyRunable(int i){
        this.i=i;
    }
    @Override
    public void run() {
        System.out.println("my runnable"+i);
    }
    public static void main(String[] args) {
        MyRunable myRunable1=new MyRunable(1);
        MyRunable myRunable2=new MyRunable(2);
        MyRunable myRunable3=new MyRunable(3);
        new Thread(myRunable1).start();
        new Thread(myRunable2).start();
        new Thread(myRunable3).start();
    }
}
