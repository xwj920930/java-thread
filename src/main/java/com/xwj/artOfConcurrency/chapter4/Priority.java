package com.xwj.artOfConcurrency.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description 线程优先级
 * @Author yuki
 * @Date 2019/2/19 19:37
 * @Version 1.0
 **/
public class Priority {
    private static volatile boolean notStart=true;
    private static volatile boolean notEnd=true;
    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs=new ArrayList<>();
        for (int i=1;i<=10;i++){
            int priority=i;
            Job job=new Job(priority);
            jobs.add(job);
            Thread thread=new Thread(job,"Thread:"+i);
            thread.setPriority(priority);
            thread.start();
        }
        //处于同一起跑线
        notStart=false;
        //此时开始为期10秒的测试
        TimeUnit.SECONDS.sleep(10);
        notEnd=false;
        for (Job job : jobs) {
            System.out.println("Job Priority:"+job.priority+",Count:"+job.jobCount);
        }
    }
    static class Job implements Runnable{
        private int priority;
        private long jobCount;
        public Job(int priority){
            this.priority=priority;
        }
        @Override
        public void run() {
            //让所有线程先进入第一个循环，相当于处于同一起跑线，避免有些线程开始了有些还在准备
            while (notStart){
                //线程由执行状态变为就绪状态，归还执行时间片
                Thread.yield();
            }
            //这个循环作为起跑线后的比赛
            while (notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
