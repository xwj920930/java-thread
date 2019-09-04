package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description interrupt不会立即中断
 * @Author yuki
 * @Date 2018/12/25 9:31
 * @Version 1.0
 **/
public class InterruptTest extends Thread{
    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        InterruptTest test=new InterruptTest();
        test.start();
        test.interrupt();
    }
}
