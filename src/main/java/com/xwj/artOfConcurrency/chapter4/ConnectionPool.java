package com.xwj.artOfConcurrency.chapter4;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Description 数据连接池
 * @Author yuki
 * @Date 2019/2/25 14:34
 * @Version 1.0
 **/
public class ConnectionPool {
    private LinkedList<Connection> pool=new LinkedList<>();
    public ConnectionPool(int initialSize){
        if (initialSize>0){
            for (int i=0;i<initialSize;i++){
                pool.addLast((Connection) ConnectionDriver.createConnection());
            }
        }
    }
    public void releaseConnection(Connection connection){
        if (connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            if (mills<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future=System.currentTimeMillis()+mills;
                long remaining=mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining=future-System.currentTimeMillis();
                }
                Connection result=null;
                if (!pool.isEmpty()){
                    result=pool.removeFirst();
                }
                return result;
            }
        }
    }
}
