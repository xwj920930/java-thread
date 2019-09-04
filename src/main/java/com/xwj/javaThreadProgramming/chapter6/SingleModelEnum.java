package com.xwj.javaThreadProgramming.chapter6;


/**
 * @Description 使用枚举类实现单例
 * 要点：
 * 1.枚举类构造方法为private的，不能new，构造方法只执行一次
 * 2.枚举类的实例默认是static final，但是不能这样写，要报错
 * @Author yuki
 * @Date 2019/1/15 19:55
 * @Version 1.0
 **/
class MyThread4 extends Thread{
    @Override
    public void run() {
        System.out.println(SingleModelEnum.MyEnum.INSTANCE.getInstance().hashCode());
    }
}
public class SingleModelEnum {
    public enum MyEnum{
        //实例
        INSTANCE;
        private SingleModelEnum singleModelEnum;
        MyEnum(){
            singleModelEnum=new SingleModelEnum();
        }
        public SingleModelEnum getInstance(){
            return singleModelEnum;
        }
    }

    public static void main(String[] args) {
//        MyThread4 thread1=new MyThread4();
//        MyThread4 thread2=new MyThread4();
//        MyThread4 thread3=new MyThread4();
//        thread1.start();
//        thread2.start();
//        thread3.start();
        System.out.println(MyEnum.INSTANCE);
        System.out.println(MyEnum.INSTANCE);
    }
}
