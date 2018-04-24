package com.tecphp.concurrency.example.commonUnsafe;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
@ThreadSafe
public class StringExample2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 50;

    public static StringBuffer count = new StringBuffer();

    public static void main(String[] arsg) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
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

        log.info("count size:{}", count.length());
    }

    public static void add() {
        count.append("1");
    }
}
