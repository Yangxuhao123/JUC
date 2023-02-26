package com.juc.threadunsafe;

public class ThreadUnsafeDemo {
    private static int data = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                for(int i =0;i<1000;i++){
                    increment();
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    increment();
                }
                //System.out.println(ThreadUnsafeDemo.data);
            }
        };
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(ThreadUnsafeDemo.data);
    }

    private synchronized static void increment(){
        ThreadUnsafeDemo.data++;
        System.out.println(ThreadUnsafeDemo.data);
    }
}
