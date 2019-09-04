package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 多线程访问不同对象的域也不会出问题
 * @Author yuki
 * @Date 2018/12/25 15:01
 * @Version 1.0
 **/
public class ThreadTest extends Thread{
    private int i;
    @Override
    public void run() {
        for (int j=0;j<5;j++){
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName()+":"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest test1=new ThreadTest();
        ThreadTest test2=new ThreadTest();
        test1.start();
        test2.start();
    }
}
