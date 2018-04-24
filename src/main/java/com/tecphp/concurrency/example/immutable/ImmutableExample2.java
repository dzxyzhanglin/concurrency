package com.tecphp.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.tecphp.concurrency.annoations.NotThreadSafe;
import com.tecphp.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableMap 修饰的不可变对象
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        // Collections.unmodifiableMap 修饰的对象值不能再被更改
        map.put(1, 8);
        log.info("{}", map.get(1)); // 抛出异常 Exception in thread "main" java.lang.UnsupportedOperationException
    }
}
