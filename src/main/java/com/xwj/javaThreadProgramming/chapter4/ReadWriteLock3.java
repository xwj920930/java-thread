package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读写/写读互斥
 * @Author yuki
 * @Date 2019/1/7 11:04
 * @Version 1.0
 **/
class MService3{
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
class ThreadAA3 extends Thread{
    private MService3 mService3;
    ThreadAA3(MService3 mService3){
        this.mService3=mService3;
    }
    @Override
    public void run() {
        mService3.read();
    }
}
class ThreadBB3 extends Thread{
    private MService3 mService3;
    ThreadBB3(MService3 mService3){
        this.mService3=mService3;
    }
    @Override
    public void run() {
        mService3.write();
    }
}
public class ReadWriteLock3 {
    public static void main(String[] args) throws InterruptedException {
        MService3 mService3=new MService3();
        ThreadAA3 aa3=new ThreadAA3(mService3);
        ThreadBB3 bb3=new ThreadBB3(mService3);
        ThreadAA3 aa4=new ThreadAA3(mService3);
        aa3.start();
        Thread.sleep(500);
        bb3.start();
        Thread.sleep(500);
        aa4.start();
    }
}
