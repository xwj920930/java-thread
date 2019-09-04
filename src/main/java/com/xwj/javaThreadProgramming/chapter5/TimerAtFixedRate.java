package com.xwj.javaThreadProgramming.chapter5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description timer.scheduleAtFixedRate
 * 对于schedule
timerTask内部执行5秒，task.schedule周期为4秒，则下一次任务开始时间紧接着上一次;
timerTask内部执行4秒，task.schedule周期为5秒，则下一次任务开始时间为上一次任务开始时间+5秒;
 * 对于scheduleAtFixedRate
timerTask内部执行5秒，task.schedule周期为4秒，则下一次任务开始时间紧接着上一次;
timerTask内部执行4秒，task.schedule周期为5秒，则下一次任务开始时间也是紧接着上一次;
 * @Author yuki
 * @Date 2019/1/14 17:06
 * @Version 1.0
 **/
public class TimerAtFixedRate {
    public static void main(String[] args) {
        //验证追赶性
        System.out.println("现在时间:"+new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)-20);
        Date runDate=calendar.getTime();
        System.out.println("计划时间:"+runDate);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task begin:"+new Date());
                System.out.println("task end:"+new Date());
            }
        }, calendar.getTime(),4000);
    }
}

