package com.xwj.artOfConcurrency.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 有界队列，理解condition用法
 * @Author yuki
 * @Date 2019/5/15 10:04
 * @Version 1.0
 **/
public class BoundedQueue<T> {
    private Object[] items;
    private int addIndex,removeIndex,count;
    private Lock lock=new ReentrantLock();
    Condition notEmpty=lock.newCondition();
    Condition notFull=lock.newCondition();
    BoundedQueue(int size){
        items=new Object[size];
    }
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            //操作
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    public void remove(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            //操作
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
