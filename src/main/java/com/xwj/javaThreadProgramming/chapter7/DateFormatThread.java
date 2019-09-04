package com.xwj.javaThreadProgramming.chapter7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description simpleDateFormat线程不安全
 * @Author yuki
 * @Date 2019/1/21 18:20
 * @Version 1.0
 **/
class MyThread extends Thread{
    private SimpleDateFormat simpleDateFormat;
    private String dateString;
    MyThread(SimpleDateFormat simpleDateFormat,String dateString){
        this.simpleDateFormat=simpleDateFormat;
        this.dateString=dateString;
    }
    @Override
    public void run() {
        try {
            Date date=simpleDateFormat.parse(dateString);
            String newDateString=simpleDateFormat.format(date).toString();
            if (!newDateString.equals(dateString)){
                System.out.println("日期错误");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
public class DateFormatThread {
    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray=new String[]{"2000-01-01","2000-01-02","2000-01-03","2000-01-04","2000-01-05"};
        MyThread[] myThreadArray=new MyThread[5];
        for (int i=0;i<5;i++){
            myThreadArray[i]=new MyThread(format,dateStringArray[i]);
        }
        for (int i=0;i<5;i++){
            myThreadArray[i].start();
        }
    }
}
