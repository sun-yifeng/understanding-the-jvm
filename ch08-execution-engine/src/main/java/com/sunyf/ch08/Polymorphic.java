package com.sunyf.ch08;

/**
 * @program: understanding-the-jvm
 * @description: 多态
 * https://mp.csdn.net/postedit/83339536
 * <p>
 * 多态机制遵循的原则概括为：
 * 当超类对象的引用变量，引用子类对象时，被引用对象的类型(子类)，而不是引用变量的类型（父类），
 * 决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法，
 * 但是它仍然要根据继承链中方法调用的优先级来确认方法，该优先级为：
 * this.show(O)
 * super.show(O)
 * this.show((super)O)
 * super.show((super)O)
 * <p>
 * 首先，分析5的调用a2.show(c)。a2是A类型的引用变量，所以this就代表了A?，
 * 它在A类中找发现没有找到show(c)，于是到A的超类中找(super)，由于A没有超类（Object除外），
 * 所以跳到第三级，也就是this.show((super)O)，C的超类有B、A1，所以，在B类中找到(super)O，
 * 打印结果为B、A1，为什么打印结果不是B、B呢？因为B、B不是被子类覆盖的方法。
 * <p>
 * 其次，分析4的调用a2.show(b)。a2是A类型的引用变量，this同样是A，这里在A中找到了show(A1 obj)
 * 同时由于a2是B类的一个引用且B类重写了show(A1 obj)，因此最终会调用子类B类的show(A1 obj)方法，结果也就是B and A1。
 * <p>
 * 输出结果：
 * 1--A and A
 * 2--A and A
 * 3--A and D
 * -----------------
 * 4--B and A
 * 5--B and A
 * 6--A and D
 * -----------------
 * 7--B and B
 * 8--B and B
 * 9--A and D
 * <p>
 * 按照同样的方法我也可以确认其他的答案。
 * @author: Mr.Sun
 * @create: 2018-10-24 11:04
 **/
public class Polymorphic {

    public static class A {

        public String show(A obj) {
            return ("A and A");
        }

        public String show(D obj) {
            return ("A and D");
        }

    }

    // B继承A
    public static class B extends A {

        public String show(A obj) {
            return ("B and A");
        }

        public String show(B obj) {
            return ("B and B");
        }

    }

    // C继承B
    public static class C extends B {
    }

    // D继承B
    public static class D extends B {
    }

    public static void main(String[] args) {

        A a1 = new A(); // a1是A类型的引用
        A a2 = new B(); // a2是A类型的引用，同时a2是B类型的引用

        B b = new B();  // b是B类型的引用
        C c = new C();  // c是C类型的引用
        D d = new D();  // d是D类型的引用

        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));

        System.out.println("-----------------");

        System.out.println("4--" + a2.show(b)); //输出不是“B and B”，是"B and A";
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));

        System.out.println("-----------------");

        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));

    }

}