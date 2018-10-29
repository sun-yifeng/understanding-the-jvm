package com.sunyf.ch08;

/**
 * @program: understanding-the-jvm
 * @description: 单分派、多分派
 * @author: Mr.Sun
 * @create: 2018-10-29 23:56
 **/
public class Dispatch {

    // QQ静态类
    static class QQ {
    }

    // 360静态类
    static class _360 {
    }

    // 父亲艰难抉择，静态类
    public static class Father {

        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }

    }

    // 儿子艰难抉择，继承父亲，静态类
    public static class Son extends Father {

        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }

    }

    // main 方法
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        //
        father.hardChoice(new _360());
        son.hardChoice(new QQ());

    }


}
