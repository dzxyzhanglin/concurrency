package com.tecphp.concurrency.example.concurrent;

import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * HashMap对应的并发容器线程安全类
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 50;

    public static Map<Integer, Integer> list = new ConcurrentHashMap<>();

    public static void main(String[] arsg) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("" + e.getMessage());
                }
                countDownLatch.countDown();
            });
        }

        // 等待直到所有的线程执行完毕
        countDownLatch.await();

        // 关闭线程池
        executorService.shutdown();

        log.info("size: {}", list.size());
    }

    public static void add(int i) {
        list.put(i, i);
    }
}
