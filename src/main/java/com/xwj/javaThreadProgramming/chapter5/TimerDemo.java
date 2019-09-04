package com.xwj.javaThreadProgramming.chapter5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 定时任务
 * @Author yuki
 * @Date 2019/1/14 14:42
 * @Version 1.0
 **/
public class TimerDemo {
    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,15);
        calendar.set(Calendar.MINUTE,15);
        calendar.set(Calendar.SECOND,0);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task begin:"+new Date());
            }
        }, calendar.getTime());
    }
}
