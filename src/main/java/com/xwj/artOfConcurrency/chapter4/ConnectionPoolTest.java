package com.xwj.artOfConcurrency.chapter4;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 线程池测试
 * @Author yuki
 * @Date 2019/2/26 18:42
 * @Version 1.0
 **/
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    public static void main(String[] args) {
        int threadCount = 10;
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }
    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            while (count>0){
                try {
                    Connection connection=pool.fetchConnection(1000);
                    if (connection!=null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
        }
    }
}
