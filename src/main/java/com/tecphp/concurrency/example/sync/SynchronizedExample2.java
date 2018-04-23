package com.tecphp.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanglin on 2018/4/23.
 */
@Slf4j
public class SynchronizedExample2 {
    // 修饰一个代码块
    public static void test1() {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 -> {}", i);
            }
        }
    }

    // 修饰一个方法
    public static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test1 -> {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2();
        });
        executorService.execute(() -> {
            example2.test2();
        });
    }
}
