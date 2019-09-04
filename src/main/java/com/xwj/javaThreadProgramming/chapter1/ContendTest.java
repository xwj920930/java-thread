package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 多线程访问相同对象的域会出问题
 * @Author yuki
 * @Date 2018/12/25 15:01
 * @Version 1.0
 **/
public class ContendTest implements Runnable{
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
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

    public static void main(String[] args){
        ContendTest test=new ContendTest();
        new Thread(test).start();
        new Thread(test).start();
    }
}
