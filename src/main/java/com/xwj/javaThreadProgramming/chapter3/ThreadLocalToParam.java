package com.xwj.javaThreadProgramming.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * threadLocal可用于传递参数
 */
public class ThreadLocalToParam {
    private static final ThreadLocal<List<Object>> RECOILS = ThreadLocal.withInitial(ArrayList::new);

    /**
     * 获取
     * @return List<WriterContextDTO>
     */
    public static List<Object> get(){
        return RECOILS.get();
    }

    /**
     * 新增
     * @param recoil 信息
     */
    public static void add(Object recoil){
        RECOILS.get().add(recoil);
    }

    /**
     * 清空
     */
    public static void clear(){
        RECOILS.remove();
    }
}