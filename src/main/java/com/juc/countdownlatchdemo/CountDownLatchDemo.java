package main.java.com.juc.countdownlatchdemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);  // AQS中的state设置为2

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程1开始执行，休眠2秒");
                    Thread.sleep(1000);
                    System.out.println("线程1准备执行countDown操作");
                    latch.countDown();
                    System.out.println("线程1完成执行countDown操作");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程2开始执行，休眠2秒");
                    Thread.sleep(1000);
                    System.out.println("线程2准备执行countDown操作");
                    latch.countDown();
                    System.out.println("线程2完成执行countDown操作");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();

        System.out.println("main线程准备执行countDownLatch的await操作，将会同步阻塞等待......");
        latch.await();
        System.out.println("所有线程都完成countDown操作，main线程的await阻塞等待结束");
    }
}
