package main.java.com.juc.semaphoredemo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    //停车场同时容纳的车辆10
    private  static  Semaphore semaphore=new Semaphore(10);
    public static void main(String[] args) throws InterruptedException {
/*        Semaphore semaphore = new Semaphore(0);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("线程1执行一个计算任务");
                    semaphore.release();
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
                    System.out.println("线程2执行一个计算任务");
                    // 这里释放一个semaphore
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();

        // 这里拿到一个semaphore
        semaphore.acquire(1);
        System.out.println("等待1个线程完成任务即可......");*/



        //模拟100辆车进入停车场
        for(int i=0;i<100;i++){

            Thread thread=new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("===="+Thread.currentThread().getName()+"来到停车场");
                        if(semaphore.availablePermits()==0){
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName()+"成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName()+"驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },i+"号车");

            thread.start();

        }
    }
}
