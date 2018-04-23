package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.Recommend;
import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式，最安全。
 * Created by zhanglin on 2018/4/23.
 */
@ThreadSafe
@Recommend
public class SigletonExample7 {
    private SigletonExample7() {

    }

    public SigletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SigletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次。
        Singleton() {
            singleton = new SigletonExample7();
        }

        public SigletonExample7 getInstance() {
            return singleton;
        }
    }
}
