package com.xwj.artOfConcurrency.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description condition实例
 * @Author yuki
 * @Date 2019/5/15 9:46
 * @Version 1.0
 **/
public class ConditionUseCase {
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }
    public void conditionSignal(){
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
