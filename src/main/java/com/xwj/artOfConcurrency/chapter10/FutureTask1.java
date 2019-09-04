package com.xwj.artOfConcurrency.chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description  FutureTask执行多任务计算的使用场景
利用FutureTask和ExecutorService，可以用多线程的方式提交计算任务，
主线程继续执行其他任务，当主线程需要子线程的计算结果时，在异步获取子线程的执行结果
 * @Author yuki
 * @Date 2019/5/23 14:24
 * @Version 1.0
 **/
class ComputeTask implements Callable<Integer>{
    private String taskName;
    ComputeTask(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public Integer call() throws Exception {
        // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成
        TimeUnit.SECONDS.sleep(5);
        System.out.println("子线程计算任务: "+taskName+" 执行完成!");
        return 1;
    }
}
public class FutureTask1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务集合，一个结果一个task
        List<FutureTask<Integer>> list=new ArrayList<>();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Integer> task=new FutureTask<>(new ComputeTask(""+i));
            list.add(task);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务
            executorService.submit(task);
        }
        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");
        // 开始统计各计算线程计算结果
        Integer result=0;
        for (FutureTask<Integer> task : list) {
            //FutureTask的get方法会自动阻塞,直到获取计算结果为止
            result+=task.get();
        }
        // 关闭线程池
        executorService.shutdown();
        System.out.println("多任务计算后的总结果是:" + result);
    }
}
