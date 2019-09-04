package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 写写互斥
 * @Author yuki
 * @Date 2019/1/7 10:53
 * @Version 1.0
 **/
class MService2{
    private ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
    public void write(){
        lock.writeLock().lock();
        System.out.println("获得写锁:"+Thread.currentThread()+":"+System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}
class ThreadAA2 extends Thread{
    private MService2 mService2;
    ThreadAA2(MService2 mService2){
        this.mService2=mService2;
    }
    @Override
    public void run() {
        mService2.write();
    }
}
class ThreadBB2 extends Thread{
    private MService2 mService2;
    ThreadBB2(MService2 mService2){
        this.mService2=mService2;
    }
    @Override
    public void run() {
        mService2.write();
    }
}
public class ReadWriteLock2 {
    public static void main(String[] args) {
        MService2 mService2=new MService2();
        ThreadAA2 aa2=new ThreadAA2(mService2);
        ThreadBB2 bb2=new ThreadBB2(mService2);
        aa2.start();
        bb2.start();
    }
}
