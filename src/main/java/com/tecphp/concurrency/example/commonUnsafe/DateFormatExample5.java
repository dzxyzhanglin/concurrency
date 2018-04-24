package com.tecphp.concurrency.example.commonUnsafe;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
@ThreadSafe
public class DateFormatExample5 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 50;

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

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
    }

    public static void add(int i) {
        log.info("time, {}, {}", i, DateTime.parse("20180220", dateTimeFormatter));
    }
}
