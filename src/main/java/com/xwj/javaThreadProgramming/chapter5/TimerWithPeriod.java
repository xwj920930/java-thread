package com.xwj.javaThreadProgramming.chapter5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 周期性timer
 * schedule(task,date,long)
 * @Author yuki
 * @Date 2019/1/14 17:06
 * @Version 1.0
 **/
public class TimerWithPeriod {
    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,19);
        calendar.set(Calendar.MINUTE,8);
        calendar.set(Calendar.SECOND,0);
        System.out.println(calendar.getTime());
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task begin:"+new Date());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task end:"+new Date());
            }
        }, calendar.getTime(),3000);
    }
}
