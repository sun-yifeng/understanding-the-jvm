package com.sunyf.ch13;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: understanding-the-jvm
 * @description: 代码清单 13-4 Atomic的原子自增
 *
 * 1、本例中解决在12章中volatile在多线程中不一定安全的问题
 * 2、本栗子使用的是原子类，底层是使用cas(compare and swap),无锁同步
 *
 * @author: Mr.Sun
 * @create: 2018-11-01 15:46
 **/
public class AtomicTest {

    public static AtomicInteger reace = new AtomicInteger(0);

    public static void increase() {
        reace.incrementAndGet(); // ?
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1)
            Thread.yield();

        System.out.println(reace);
    }

}
