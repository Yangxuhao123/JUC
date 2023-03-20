package main.java.com.juc.cyclicbarrierdemo;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都完成了自己的任务，现在可以合并结果了......");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程1 执行自己的一部分工作......");
                    barrier.await();
                    System.out.println("最终结果合并完成，线程1可以退出......");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程2 执行自己的一部分工作......");
                    barrier.await();
                    System.out.println("最终结果合并完成，线程2可以退出......");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程3执行自己的一部分工作......");
                    barrier.await();
                    System.out.println("最终结果合并完成，线程3可以退出......");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
