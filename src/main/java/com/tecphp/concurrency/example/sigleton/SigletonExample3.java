package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.NotRecommend;
import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时创建
 * Created by zhanglin on 2018/4/23.
 */
@ThreadSafe
@NotRecommend
public class SigletonExample3 {
    // 私有构造函数
    private SigletonExample3() {

    }

    // 单例对象
    private static SigletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized SigletonExample3 getInstance() {
        // 使用了synchronized修饰，线程安全。但是不推荐使用。
        if (instance == null) {
            instance = new SigletonExample3();
        }
        return instance;
    }
}
