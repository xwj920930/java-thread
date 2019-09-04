package com.xwj.artOfConcurrency.chapter6;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description 延迟队列测试
 * @Author yuki
 * @Date 2019/5/17 14:26
 * @Version 1.0
 **/
class MyDelayTask implements Delayed{
    private String name;//存储的结构
    private Long start=System.currentTimeMillis();
    private Long time;//延迟时长
    MyDelayTask(String name,Long time){
        this.name=name;
        this.time=time;
    }
    @Override//延迟时间，delayQueue自动调用,过期时间-当前时间
    public long getDelay(TimeUnit unit) {
        return unit.convert(start+time-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }
    @Override//队列内部比较排序规则
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MyDelayedTask{" + "name='" + name + '\'' + ", time=" + time + '}';
    }
}
public class DelayQueueTest {
    private static DelayQueue<MyDelayTask> delayQueue=new DelayQueue<>();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            delayQueue.offer(new MyDelayTask("task1",10000L));
            delayQueue.offer(new MyDelayTask("task2",6000L));
            delayQueue.offer(new MyDelayTask("task3",3000L));
            delayQueue.offer(new MyDelayTask("task4",2000L));
            delayQueue.offer(new MyDelayTask("task5",900L));
        }).start();
        while (true){
            MyDelayTask take = delayQueue.take();
            System.out.println(take);
        }
    }
}
