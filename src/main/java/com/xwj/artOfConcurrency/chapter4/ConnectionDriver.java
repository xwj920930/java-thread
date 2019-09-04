package com.xwj.artOfConcurrency.chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @Description 动态代理
 * @Author yuki
 * @Date 2019/2/25 14:58
 * @Version 1.0
 **/
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }
    public static final Object createConnection(){
        return Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class[]{Connection.class},new ConnectionHandler());
    }
}
