package com.xwj.javaThreadProgramming.chapter2;

/**
 * @Description 用volatile解决异步死循环
 * @Author yuki
 * @Date 2018/12/28 9:45
 * @Version 1.0
 **/
class MyThread extends Thread{
    private volatile boolean isContinue=true;
    public boolean isContinue() {
        return isContinue;
    }
    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
    @Override
    public void run() {
        while (isContinue==true){
        }
        System.out.println("stop");
    }
}

public class DeadCycleTest2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread=new MyThread();
        //新增线程
        myThread.start();
        Thread.sleep(1000);
        //主线程
        myThread.setContinue(false);
        System.out.println("已经赋值false");
    }
}
