package com.tecphp.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.tecphp.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final 修饰的不可变对象
 * Created by zhanglin on 2018/4/24.
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
    }

    public static void main(String[] args) {
        // final 修饰的变量值不能被修改
        // a = 2;
        // b = "3";

        // final 修饰的引用不能再次被初始化
        // map = Maps.newHashMap();
        // 但是里面的值可以被修改，如：
        map.put(1, 8);
        log.info("{}", map.get(1)); // 8
    }
}
