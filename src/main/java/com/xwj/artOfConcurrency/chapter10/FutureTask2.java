package com.xwj.artOfConcurrency.chapter10;

import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description FutureTask在高并发环境下确保任务只执行一次
在很多高并发的环境下，往往我们只需要某些任务只执行一次。这种使用情景FutureTask的特性
恰能胜任。举一个例子，假设有一个带key的连接池，当key存在时，即直接返回key对应的对象；
当key不存在时，则创建连接
 * @Author yuki
 * @Date 2019/5/23 14:54
 * @Version 1.0
 **/
public class FutureTask2 {
    private ConcurrentHashMap<String,FutureTask<Connection>> pool=new ConcurrentHashMap<>();
    private Connection createConnection(){return null;}
    public Connection getConnection(String key) throws ExecutionException, InterruptedException {
        FutureTask<Connection> futureTask = pool.get(key);
        if (futureTask!=null){
            return futureTask.get();
        }else {
            Callable<Connection> callable=new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<>(callable);
            FutureTask<Connection> connectionFutureTask = pool.putIfAbsent(key, newTask);
            if (connectionFutureTask==null){
                connectionFutureTask=newTask;
                connectionFutureTask.run();
            }
            return connectionFutureTask.get();
        }
    }
}
