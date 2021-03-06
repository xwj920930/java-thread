package com.xwj.javaThreadProgramming.chapter6;

import java.io.*;

/**
 * @Description 序列化的单例，针对序列化前后对象改变
 * @Author yuki
 * @Date 2019/1/15 19:45
 * @Version 1.0
 **/
public class SingletonStaticInnerSerializeTest {

    public static void main(String[] args) {
        try {
            SingletonStaticInnerSerialize serialize = SingletonStaticInnerSerialize.getInstance();
            System.out.println(serialize.hashCode());
            //序列化
            FileOutputStream fo = new FileOutputStream("D:\\test\\test.txt");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(serialize);
            oo.close();
            fo.close();
            //反序列化
            FileInputStream fi = new FileInputStream("D:\\test\\test.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);
            SingletonStaticInnerSerialize serialize2 = (SingletonStaticInnerSerialize) oi.readObject();
            oi.close();
            fi.close();
            System.out.println(serialize2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//使用匿名内部类实现单例模式，在遇见序列化和反序列化的场景，得到的不是同一个实例
//解决这个问题是在序列化的时候使用readResolve方法，即去掉注释的部分
class SingletonStaticInnerSerialize implements Serializable {

    /**
     * 2018年03月28日
     */
    private static final long serialVersionUID = 1L;

    private static class InnerClass {
        private static SingletonStaticInnerSerialize singletonStaticInnerSerialize = new SingletonStaticInnerSerialize();
    }

    public static SingletonStaticInnerSerialize getInstance() {
        return InnerClass.singletonStaticInnerSerialize;
    }

  protected Object readResolve() {
      System.out.println("调用了readResolve方法");
      return InnerClass.singletonStaticInnerSerialize;
  }
}
