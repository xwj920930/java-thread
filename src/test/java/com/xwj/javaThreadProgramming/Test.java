package com.xwj.javaThreadProgramming;

/**
 * @Description TODO
 * @Author yuki
 * @Date 2019/6/25 7:30
 * @Version 1.0
 **/
class ThreadA extends Thread{
    @Override
    public void run() {
        System.out.println("start");
        Thread.yield();
        System.out.println("end");
    }
}
public class Test {
    public static void main(String[] args) {
        new ThreadA().start();
    }
}
