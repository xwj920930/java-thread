package com.xwj.artOfConcurrency.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @Description threadLocal
 * @Author yuki
 * @Date 2019/2/22 11:18
 * @Version 1.0
 **/
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL= new ThreadLocal<>();
    private static void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    private static long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }
    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
