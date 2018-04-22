package com.tecphp.concurrency.example.atomic;

import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zhanglin on 2018/4/22.
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {
    /**
     * 更新某个类中的某个字段的值，
     * 该字段必须用 volatile 修饰。
     */
    private static AtomicIntegerFieldUpdater<AtomicExample3> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample3.class, "count");

    @Getter
    public volatile int count = 100;

    private static AtomicExample3 example3 = new AtomicExample3();

    public static void main(String[] arsg) throws InterruptedException {
        if (updater.compareAndSet(example3, 100, 120)) {
            log.info("update success1,count, {}", example3.getCount());
        }

        if (updater.compareAndSet(example3, 100, 120)) {
            log.info("update success2,count, {}", example3.getCount());
        } else {
            log.info("update fail2,count, {}", example3.getCount());
        }
    }
}
