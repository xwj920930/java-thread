package com.xwj.javaThreadProgramming.chapter2;

/**
 * @Description 模拟死锁
 * 查询死锁方法：1.jps查到id 2.jstack -l id
 * @Author yuki
 * @Date 2018/12/27 15:18
 * @Version 1.0
 **/
class DeadThread implements Runnable{
    public String username;
    public Object lock1=new Object();
    public Object lock2=new Object();
    public void setFlag(String username){
        this.username=username;
    }
    @Override
    public void run() {
        if (username.equals("a")){
            synchronized (lock1){
                System.out.println("username="+username);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("lock1->lock2");
                }
            }
        }
        if (username.equals("b")){
            synchronized (lock2){
                System.out.println("username="+username);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("lock2->lock1");
                }
            }
        }
    }
}
public class DeadLockTest {
    public static void main(String[] args) throws InterruptedException {
        DeadThread thread=new DeadThread();
        thread.setFlag("a");
        Thread thread1=new Thread(thread);
        thread1.start();
        thread1.sleep(100);
        thread.setFlag("b");
        Thread thread2=new Thread(thread);
        thread2.start();
    }
}
