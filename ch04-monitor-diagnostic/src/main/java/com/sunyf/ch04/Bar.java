package com.sunyf.ch04;

/**
 * @program: understanding-the-jvm
 * @description: JIT生成代码反汇编
 *
 * @author: Mr.Sun
 * @create: 2018-10-31 19:26
 **/
public class Bar {

    int a = 1;
    static  int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        new Bar().sum(3);
    }
}
