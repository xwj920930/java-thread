package com.xwj.javaThreadProgramming.chapter3;

import java.util.Date;

/**
 * @Description inheritableThreadLocal测试
 * 子线程可以使用父线程的值
 * @Author yuki
 * @Date 2019/1/3 10:32
 * @Version 1.0
 **/
class InheritableLocal extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date();
    }
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue+"：子线程";
    }
}
class InheritableTool{
    public static InheritableLocal local=new InheritableLocal();
}
class ThreadAA extends Thread{
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("threadAA:"+InheritableTool.local.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class InheritableThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<10;i++){
            System.out.println("main:"+InheritableTool.local.get());
            Thread.sleep(100);
        }
        Thread.sleep(3000);
        ThreadAA aa=new ThreadAA();
        aa.start();
    }
}
