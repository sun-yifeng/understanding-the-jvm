package com.sunyf.ch13;

import java.util.Vector;

/**
 * @program: understanding-the-jvm
 * @description: 代码清单 13-2 Vector线程安全测试
 *
 * 1、java语言中，大部分线程安全类都是相当安全，如Vector、HashTable等
 * 2、java语言中，不可变类型有，String、Enum、Long、Double、BigInteger、BigDecimal等包装类型
 *
 * @author: Mr.Sun
 * @create: 2018-11-01 14:29
 **/
public class VectorTest1 {

    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            //
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector){
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            // 获取当前活动的线程数
            while (Thread.activeCount() > 20);
        }

    }

}
