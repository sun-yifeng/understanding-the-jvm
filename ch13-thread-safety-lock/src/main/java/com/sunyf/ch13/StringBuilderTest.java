package com.sunyf.ch13;

/**
 * @program: understanding-the-jvm
 * @description: 13.3.2 锁消除测试
 * @author: Mr.Sun
 * @create: 2018-11-01 16:23
 **/
public class StringBuilderTest {

    String str = "str";

    public static void main(String[] args) {
        StringBuffer  bf = new StringBuffer("bf");
        StringBuilder bd = new StringBuilder("bd");
        bf.append(",StringBuffer is synchronized");
        bd.append(",StringBuilder is not synchronized");
        //
    }
}
