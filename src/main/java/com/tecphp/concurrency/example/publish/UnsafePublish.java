package com.tecphp.concurrency.example.publish;

import com.tecphp.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by zhanglin on 2018/4/23.
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        // 其他线程会改变states中的值，所以线程不安全。
    }
}
