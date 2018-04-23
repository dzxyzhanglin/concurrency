package com.tecphp.concurrency.example.count;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhanglin on 2018/4/22.
 */
@Slf4j
@NotThreadSafe
public class CountExample3 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 50;

    public static volatile int count = 0;

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

        log.info("count:{}", count);
    }

    public static void add() {
        count++;
        //count用volatile修饰
        //1、从主存中取出count
        //2、count + 1
        //3、count写回主存
        //如果有多个线程同时操作的话， 就可能会丢掉一些值。
    }
}
