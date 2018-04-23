package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时创建
 * Created by zhanglin on 2018/4/23.
 */
@ThreadSafe
public class SigletonExample6 {
    // 私有构造函数
    private SigletonExample6() {

    }

    // 单例对象
    private static SigletonExample6 instance = null;

    static {
        instance = new SigletonExample6();
    }

    // 静态的工厂方法
    public static SigletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
