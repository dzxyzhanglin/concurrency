package com.tecphp.concurrency.example.sigleton;

import com.tecphp.concurrency.annoations.ThreadSafe;

/**
 * 练习枚举，线程安全
 * Created by zhanglin on 2018/4/24.
 */
@ThreadSafe
public class ExerciseSigleton2 {

    private ExerciseSigleton2() {

    }

    public static ExerciseSigleton2 getInstance() {
        return Sigleton.INSTANCE.getInstance();
    }

    private enum Sigleton {
        INSTANCE,
        TEST;

        private ExerciseSigleton2 sigleton = null;

        Sigleton() {
            sigleton = new ExerciseSigleton2();
        }

        public ExerciseSigleton2 getInstance() {
            return sigleton;
        }
    }

    public static void main(String[] args) {
        System.out.println(Sigleton.INSTANCE.getInstance().hashCode()); // 1067040082
        System.out.println(Sigleton.TEST.getInstance().hashCode()); // 1325547227
        System.out.println(getInstance().hashCode()); // 1067040082
        System.out.println(getInstance().hashCode()); // 1067040082
    }
}
