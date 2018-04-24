package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 练习懒汉模式单例线程安全
 * Created by zhanglin on 2018/4/24.
 */
@ThreadSafe
public class ExerciseSigleton1 {

    private ExerciseSigleton1() {

    }

    private static volatile ExerciseSigleton1 instance = null;

    public static ExerciseSigleton1 getInstance() {
        if (instance == null) {
            synchronized (ExerciseSigleton1.class) {
                if (instance == null) {
                    instance = new ExerciseSigleton1();
                }
            }
        }
        return instance;
    }
}
