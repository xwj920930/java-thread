package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读读共享
 * @Author yuki
 * @Date 2019/1/7 10:33
 * @Version 1.0
 **/
class MService1{
    private ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
    public void read(){
        lock.readLock().lock();
        System.out.println("获得读锁:"+Thread.currentThread()+":"+System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}
class ThreadAA1 extends Thread{
    private MService1 mService1;
    ThreadAA1(MService1 mService1){
        this.mService1=mService1;
    }
    @Override
    public void run() {
        mService1.read();
    }
}
class ThreadBB1 extends Thread{
    private MService1 mService1;
    ThreadBB1(MService1 mService1){
        this.mService1=mService1;
    }
    @Override
    public void run() {
        mService1.read();
    }
}
public class ReadWriteLock1 {
    public static void main(String[] args) {
        MService1 mService1=new MService1();
        ThreadAA1 aa1=new ThreadAA1(mService1);
        ThreadBB1 bb1=new ThreadBB1(mService1);
        aa1.start();
        bb1.start();
    }
}
