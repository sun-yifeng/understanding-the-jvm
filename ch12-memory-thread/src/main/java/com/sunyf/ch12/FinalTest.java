package com.sunyf.ch12;

/**
 * @program: understanding-the-jvm
 * @description: 代码清单 12-7 final可见性
 * @author: Mr.Sun
 * @create: 2018-11-01 01:39
 **/
public class FinalTest {

    public static final int i; // 相当于C++中的声明

    public final int j;

    static {
        i = 1; // 相当于C++中的赋值，赋值之后final变量不能再修改
        //do sth
    }

    {
        // do sth
        j = 2;
        // do sth

    }

    public static int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public static void main(String[] args) {
        FinalTest f = new FinalTest();
        System.out.println("i:" + f.getI());
        System.out.println("j:" + f.getJ());
    }

}
