package com.xwj.javaThreadProgramming.chapter6;

/**
 * @Description 初始化类的时候属性
 * @Author yuki
 * @Date 2019/1/22 16:26
 * @Version 1.0
 **/
class Test1{}
public class Test {
    private Test1 test1=new Test1();
    public static void main(String[] args) {
        Test test=new Test();
        System.out.println(test.test1.hashCode());
        System.out.println(test.test1.hashCode());
    }
}
