package com.juc.doublechecksingleton;

import java.security.interfaces.DSAPublicKey;

public class DoubleCheckSingleton {
    // 如果你不加volatile的话，线程2加载到工作内存的instance还是null
    // 继续创建一个实例
    // 所以为了保证可见性，必须要让线程1创建的实例刷到主内存
    // 所以使用volatile关键字，保证可见性
    private static volatile DoubleCheckSingleton instance;

    Object socket;

    public DoubleCheckSingleton(){
        socket = new Object();
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            // 多个线程会卡在这儿
            synchronized (DoubleCheckSingleton.class) {
                // 有一个线程先进来
                // 第二个线程进来了，此时如果没有这个double check的判断的话
                // 然后就会导致他再次创建一遍实例
                if (instance == null) {
                    // 创建一个单例
                    instance = new DoubleCheckSingleton();
                    // 对象初始化的过程是粉几个步骤的：1）初始化一块内存空间，2）指针指向内存空间，3）给对象里的变量进行赋值
                    // 有可能会出现一种情况，就是指令重排，可能会导致说   3）  1）  2）  没有给对象里的变量赋值成功   初始化内存空间   指向内存空间
                    // DoubleCheckSingleton对象里的socket对象还没在构造函数里初始化，还是null
                    // 但是这个对象对应的内存空间和地址已经分配好了，指针指过去了
                    // 此时还没释放锁
                    // 但是此时DoubleCheckSingleton对象不是null，但是socket是null

                    //加了volatile之后 加了内存屏障  只有先写了 才能读，禁止重排序。
                }
                // 第一个线程出来了
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 开了两个线程
        // 线程1
        DoubleCheckSingleton instance1 = DoubleCheckSingleton.getInstance();
        System.out.println(instance1);

        // 线程2
        DoubleCheckSingleton instance2 = DoubleCheckSingleton.getInstance();
        // 此时线程2直接拿到一个instance2的对象，但是里面的socket对象是null
        // 此时如果线程2来使用instance2对象内部的socket对象，会出现空指针异常
        instance2.socket.toString();

    }
}
