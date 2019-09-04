package com.xwj.javaThreadProgramming.chapter5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 多个timerTask不会同时运行，会形成队列
 * @Author yuki
 * @Date 2019/1/14 16:58
 * @Version 1.0
 **/
class MyTask1 extends TimerTask{
    @Override
    public void run() {
        System.out.println("运行了，时间为"+new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyTask2 extends TimerTask{
    @Override
    public void run() {
        System.out.println("运行了，时间为"+new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class MultiTimerTask {
    public static void main(String[] args) {
        MyTask1 task1=new MyTask1();
        MyTask2 task2=new MyTask2();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,15);
        calendar.set(Calendar.MINUTE,15);
        calendar.set(Calendar.SECOND,0);
        Timer timer=new Timer();
        timer.schedule(task1,calendar.getTime());
        timer.schedule(task2,calendar.getTime());
    }
}
