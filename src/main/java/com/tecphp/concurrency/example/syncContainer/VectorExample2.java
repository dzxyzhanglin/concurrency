package com.tecphp.concurrency.example.syncContainer;

import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
public class VectorExample2 {

    // Exception in thread "main" java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v) { // foreach
        for (Integer i: v) {
            log.info("i = {}", i);
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    // Exception in thread "main" java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v) { // iterator
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v) { // for
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        // test1 、 test2 都会抛出异常。

        test1(vector);
    }
}
