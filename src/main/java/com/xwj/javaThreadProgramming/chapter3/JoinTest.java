package com.xwj.javaThreadProgramming.chapter3;

/**
 * @Description 测试join
 * @Author yuki
 * @Date 2019/1/2 15:35
 * @Version 1.0
 **/
class JoinThread extends Thread{
    @Override
    public void run() {
        int second= (int) (Math.random()*1000);
        System.out.println(second);
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread=new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println("after joinThread");
    }
}
