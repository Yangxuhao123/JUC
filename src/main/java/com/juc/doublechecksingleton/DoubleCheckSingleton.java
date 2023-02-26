package com.juc.doublechecksingleton;

public class DoubleCheckSingleton {
    // 如果你不加volatile的话，线程2加载到工作内存的instance还是null
    // 继续创建一个实例
    // 所以为了保证可见性，必须要让线程1创建的实例刷到主内存
    // 所以使用volatile关键字，保证可见性
    private static volatile DoubleCheckSingleton instance;

    public DoubleCheckSingleton getInstance() {
        if (instance == null) {
            // 多个线程会卡在这儿
            synchronized (DoubleCheckSingleton.class) {
                // 有一个线程先进来
                // 第二个线程进来了，此时如果没有这个double check的判断的话
                // 然后就会导致他再次创建一遍实例
                if (instance == null) {
                    // 创建一个单例
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
