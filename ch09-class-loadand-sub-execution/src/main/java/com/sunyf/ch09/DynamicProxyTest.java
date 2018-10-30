package com.sunyf.ch09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: understanding-the-jvm
 * @description: 动态代理的简单例子
 * @author: Mr.Sun
 * @create: 2018-10-30 14:23
 **/
public class DynamicProxyTest {

    // 接口类
    interface IHello {
        void sayHello();
    }

    // 实现类
    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    // 实现InvocationHandler
    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        // bind方法
        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            // 反射（类加载器），返回实例
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(), this);
        }

        // invoke方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }

    }

    public static void main(String[] args) {
        // 创建DynamicProxy实例bind到
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
