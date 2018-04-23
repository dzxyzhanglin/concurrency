package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 使用了volatile关键字，不指令重排。【线程安全】
 * 单例实例在第一次使用时创建
 * Created by zhanglin on 2018/4/23.
 */
@ThreadSafe
public class SigletonExample5 {
    // 私有构造函数
    private SigletonExample5() {

    }

    //1、memory = allocate() 分配对象的内存空间
    //2、初始化对象
    //3、instance = memory 设置instance指向刚分配的内存地址

    // 单例对象
    // volatile + 双重检测机制 -> 禁止指令重排，保证线程安全
    private static volatile SigletonExample5 instance = null;

    // 静态的工厂方法
    public static SigletonExample5 getInstance() {
        if (instance == null) {// 双重检测机制
            synchronized (SigletonExample5.class) { // 同步锁
                if (instance == null) {
                    instance = new SigletonExample5();
                }
            }
        }
        return instance;
    }
}
