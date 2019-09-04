package com.xwj.javaThreadProgramming.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author yuki
 * @Date 2019/1/4 15:46
 * @Version 1.0
 **/
class MySevice extends Thread{
    private ReentrantLock lock=new ReentrantLock();
    void method(){
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
    @Override
    public void run() {
        method();
    }
}
public class Test {
    public static void main(String[] args) {
        MySevice myService=new MySevice();
        myService.start();
    }
}
