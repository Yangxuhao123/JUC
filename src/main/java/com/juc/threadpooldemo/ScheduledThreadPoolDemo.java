package main.java.com.juc.threadpooldemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟5秒钟执行的任务");
            }
        },5, TimeUnit.SECONDS);

        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("每隔秒钟执行的任务");
            }
        },5,3,TimeUnit.SECONDS);
    }
}
