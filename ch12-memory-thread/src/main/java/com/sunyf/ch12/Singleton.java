package com.sunyf.ch12;

/**
 * @program: understanding-the-jvm
 * @description: 使用双锁检查（DCL）实现单例模式
 * @author: Mr.Sun
 * @create: 2018-10-31 16:39
 **/
public class Singleton {

    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
