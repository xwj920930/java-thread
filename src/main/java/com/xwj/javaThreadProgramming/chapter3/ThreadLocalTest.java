package com.xwj.javaThreadProgramming.chapter3;

/**
 * @Description 测试threadLocal
 * @Author yuki
 * @Date 2019/1/2 17:04
 * @Version 1.0
 **/
class Tools{
    public static ThreadLocal local=new ThreadLocal();
}
class T extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return "init";
    }
}
class ThreadA extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            Tools.local.set("ThreadA "+(i+1));
            System.out.println("ThreadA get value "+Tools.local.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadB extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            Tools.local.set("ThreadB "+(i+1));
            System.out.println("ThreadB get value "+Tools.local.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadA threadA=new ThreadA();
        ThreadB threadB=new ThreadB();
        threadA.start();
        threadB.start();
        for (int i=0;i<100;i++){
            Tools.local.set("ThreadMain "+(i+1));
            System.out.println("ThreadMain get value "+Tools.local.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        T t=new T();
//        System.out.println(t.get());
    }
}
