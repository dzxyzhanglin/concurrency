package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时创建
 * Created by zhanglin on 2018/4/23.
 */
@ThreadSafe
public class SigletonExample2 {
    // 私有构造函数
    private SigletonExample2() {

    }

    // 单例对象
    private static SigletonExample2 instance = new SigletonExample2();

    // 静态的工厂方法
    public static SigletonExample2 getInstance() {
        return instance;
    }
}
