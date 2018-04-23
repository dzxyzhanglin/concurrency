package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式  【线程不安全】
 * 单例实例在第一次使用时创建
 * Created by zhanglin on 2018/4/23.
 */
@NotThreadSafe
public class SigletonExample4 {
    // 私有构造函数
    private SigletonExample4() {

    }

    //1、memory = allocate() 分配对象的内存空间
    //2、初始化对象
    //3、instance = memory 设置instance指向刚分配的内存地址

    //JVM和cpu优化，发生了指令重排

    //1、memory = allocate() 分配对象的内存空间
    //3、instance = memory 设置instance指向刚分配的内存地址
    //2、初始化对象

    // 单例对象
    private static SigletonExample4 instance = null;

    // 静态的工厂方法
    public static SigletonExample4 getInstance() {
        if (instance == null) {// 双重检测机制
            synchronized (SigletonExample4.class) { // 同步锁
                if (instance == null) {
                    instance = new SigletonExample4();
                }
            }
        }
        return instance;
    }
}
