package com.xwj.artOfConcurrency.chapter1;

/**
 * @Description 并发小的情况下并发不一定比串行快
 * @Author yuki
 * @Date 2019/2/14 15:24
 * @Version 1.0
 **/
public class ConcurrencyTest {
    private static final long count=10000L;
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start=System.currentTimeMillis();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int a=0;
                for(long i=0;i<count;i++){
                    a+=5;
                }
            }
        });
        thread.start();
        int b=0;
        for(long i=0;i<count;i++){
            b--;
        }
        long time=System.currentTimeMillis()-start;
//        thread.join();
        System.out.println("concurrency :" + time+"ms,b="+b);
    }
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time+"ms,b="+b+",a="+a);
    }
}
