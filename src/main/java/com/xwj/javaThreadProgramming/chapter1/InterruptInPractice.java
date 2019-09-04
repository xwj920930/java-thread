package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 实际工作中的线程停止
 * @Author yuki
 * @Date 2018/12/25 9:42
 * @Version 1.0
 **/
public class InterruptInPractice extends Thread{
    @Override
    public void run() {
        try {
            for (int i=0;i<10000;i++){
                System.out.println(i);
                if (this.isInterrupted()){
                    System.out.println("stop thread");
                    throw new InterruptedException();
                }
            }
            System.out.println("after stop");
        }
        catch (InterruptedException e){
        }
    }
    public static void main(String[] args) throws InterruptedException {
        InterruptInPractice interrupt=new InterruptInPractice();
        interrupt.start();
        Thread.sleep(20);
        interrupt.interrupt();
    }
}
