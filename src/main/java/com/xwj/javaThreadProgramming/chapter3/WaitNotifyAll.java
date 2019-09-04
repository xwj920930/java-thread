package com.xwj.javaThreadProgramming.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * 生产者消费者模式中用while唤醒线程而不是if
if：wait唤醒以后继续向下执行
while：wait唤醒以后执行大括号里面的语句，
执行后再次进入while条件进行判断，发现不符合要求，
继续进入循环体直到遇到wait后继续等待
 * @Author yuki
 * @Date 2018/12/29 15:41
 * @Version 1.0
 **/
class Add{
    private String lock;
    Add(String lock){
        this.lock=lock;
    }
    public void add(){
        synchronized (lock){
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}
class Sub{
    private String lock;
    Sub(String lock){
        this.lock=lock;
    }
    public void sub() throws InterruptedException {
        synchronized (lock){
            while (ValueObject.list.size()==0){
                System.out.println("start wait:"+Thread.currentThread());
                lock.wait();
                System.out.println("end wait:"+Thread.currentThread());
            }
            ValueObject.list.remove(0);
            System.out.println("list size="+ValueObject.list.size());
            lock.notifyAll();
        }
    }
}
class ThreadAdd extends Thread{
    private Add add;
    ThreadAdd(Add add){
        this.add=add;
    }
    @Override
    public void run() {
        add.add();
    }
}
class ThreadSub extends Thread{
    private Sub sub;
    ThreadSub(Sub sub){
        this.sub=sub;
    }
    @Override
    public void run() {
        try {
            sub.sub();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ValueObject{
    public static List list=new ArrayList();
    public static String value="";
}
public class WaitNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        String lock="";
        Add add=new Add(lock);
        Sub sub=new Sub(lock);
        ThreadAdd threadAdd=new ThreadAdd(add);
        ThreadSub threadSub1=new ThreadSub(sub);
        ThreadSub threadSub2=new ThreadSub(sub);
        threadSub1.start();
        threadSub2.start();
        Thread.sleep(1000);
        threadAdd.start();
    }
}
