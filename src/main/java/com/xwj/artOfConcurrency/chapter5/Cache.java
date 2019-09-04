package com.xwj.artOfConcurrency.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读写锁模拟缓存
 * @Author yuki
 * @Date 2019/5/15 9:13
 * @Version 1.0
 **/
public class Cache {
    private static Map<String,Object> map=new HashMap<>();
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock read=readWriteLock.readLock();
    private static Lock write=readWriteLock.writeLock();
    public static final Object get(String key){
        read.lock();
        try {
            return map.get(key);
        } finally {
            read.unlock();
        }
    }
    public static final Object put(String key,Object value){
        write.lock();
        try {
            return map.put(key,value);
        } finally {
            write.unlock();
        }
    }
    public static final void clear(){
        write.lock();
        try {
            map.clear();
        } finally {
            write.unlock();
        }
    }
}
