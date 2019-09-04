package com.xwj.javaThreadProgramming.chapter7;

/**
 * @Description 多级关联线程组
 * ThreadGroup group=new ThreadGroup(parentGroup,name)
 * @Author yuki
 * @Date 2019/1/17 19:04
 * @Version 1.0
 **/
class MyThread2 extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("run method!"+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadGroup2 {
    public static void main(String[] args) {
        ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();
        ThreadGroup group=new ThreadGroup(mainGroup,"group");
        MyThread2 thread2=new MyThread2();
        Thread t1=new Thread(group,thread2);
        t1.setName("Z");
        t1.start();
        System.out.println("threadGroupCount:"+group.activeCount());
        System.out.println("threadGroupName:"+group.getName());
    }
}
