package com.xwj.javaThreadProgramming.chapter1;

/**
 * @Description 线程暂停和恢复
 * @Author yuki
 * @Date 2018/12/25 10:50
 * @Version 1.0
 **/
public class SuspendAndResume extends Thread{
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SuspendAndResume resume=new SuspendAndResume();
        resume.start();
        Thread.sleep(100);
        resume.suspend();
        Thread.sleep(5000);
        resume.resume();
    }
}
