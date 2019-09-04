package com.xwj.javaThreadProgramming.chapter2;

/**
 * @Description 用多线程解决同步死循环
 * @Author yuki
 * @Date 2018/12/28 9:45
 * @Version 1.0
 **/
class PrintString implements Runnable{
    private boolean isContinue=true;
    public boolean isContinue() {
        return isContinue;
    }
    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
    public void  printMethod(){
        while (isContinue==true){
            System.out.println("run printMethod;"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        printMethod();
    }
}
public class DeadCycleTest {
    public static void main(String[] args) throws InterruptedException {
        PrintString printString=new PrintString();
        new Thread(printString).start();
        Thread.sleep(100);
        System.out.println("stop it");
        printString.setContinue(false);
    }
}
