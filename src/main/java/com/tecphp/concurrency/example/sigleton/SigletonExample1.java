package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时创建
 * Created by zhanglin on 2018/4/23.
 */
@NotThreadSafe
public class SigletonExample1 {
    // 私有构造函数
    private SigletonExample1() {

    }

    // 单例对象
    private static SigletonExample1 instance = null;

    // 静态的工厂方法
    public static SigletonExample1 getInstance() {
        // 在单线程下没有问题，但是多线程可能会实例化多个。
        // 线程不安全。
        if (instance == null) {
            instance = new SigletonExample1();
        }
        return instance;
    }
}
