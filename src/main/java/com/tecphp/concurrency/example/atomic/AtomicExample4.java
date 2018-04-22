package com.tecphp.concurrency.example.atomic;

import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhanglin on 2018/4/22.
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] arsg) throws InterruptedException {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 8);

        log.info("count ,{}", count);
    }
}
