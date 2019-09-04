package com.xwj.artOfConcurrency.chapter6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description 测试fork/join框架
 * 计算1+2+...+10亿，分割(阈值)为1000
 * @Author yuki
 * @Date 2019/5/20 10:01
 * @Version 1.0
 **/
class CountTask extends RecursiveTask<Long>{
    private Long start;
    private Long end;
    private static final Long MAX=1000000000L;
    private static final Long THRESHOLD=1000L;
    CountTask(Long start,Long end){
        this.start=start;
        this.end=end;
    }
    CountTask(){
        this.start=1L;
        this.end=MAX;
    }
    @Override
    protected Long compute() {
        long sum=0;
        if (end-start<=THRESHOLD){
            for (long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            long mid=(start+end)/2;
            CountTask task1=new CountTask(start,mid);
            CountTask task2=new CountTask(mid+1,end);
            task1.fork();
            task2.fork();
            return task1.join()+task2.join();
        }
    }
}
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Long value = forkJoinPool.invoke(new CountTask());
        System.out.println(value);
    }
}
