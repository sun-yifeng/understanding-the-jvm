package com.sunyf.ch12;

/**
 * @program: understanding-the-jvm
 * @description: volatile的运算
 *
 * 1、分析字节码的工具
 * javap -verbose VolatileTest
 *
 * @author: Mr.Sun
 * @create: 2018-10-31 15:31
 **/
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase () {
        race ++ ; // 此处有四条汇编指令
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        // 发起20个线程，每个线程对race变量执行1000次自增，如果正确执行race结果应该是20000
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        //increase();
                        race ++;
                    }
                }
            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1)
            Thread.yield();

        System.out.println(race);

    }
}
