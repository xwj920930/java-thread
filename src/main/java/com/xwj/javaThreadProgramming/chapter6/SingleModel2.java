package com.xwj.javaThreadProgramming.chapter6;


/**
 * @Description 懒汉模式
 * 加载类的时候，new属性，调用同一对象的该属性时属性为同一个，并不会额外new
 * 单例模式虽然属性可以new出来，但是该类的实例只有一个，所以里面new的属性也只有一个
 * 单例模式下会共享普通成员变量和静态成员变量,多例模式下普通成员变量不共享,静态成员共享
 * @Author yuki
 * @Date 2019/1/15 18:23
 * @Version 1.0
 **/
class MyObject2{
    private Test test=new Test();
    public Test getTest() {
        return test;
    }
    private volatile static MyObject2 myObject2;
    public static MyObject2 getInstance(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (MyObject2.class){
            if (myObject2==null){
                myObject2=new MyObject2();
            }
        }
        return myObject2;
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("instance"+MyObject2.getInstance().hashCode());
        System.out.println(MyObject2.getInstance().getTest().hashCode());
    }
}

public class SingleModel2 {
    public static void main(String[] args) {
        MyThread2 thread1=new MyThread2();
        MyThread2 thread2=new MyThread2();
        MyThread2 thread3=new MyThread2();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
