package main.java.com.juc.linkedblockingqueuedemo;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        queue.put("张三");
        System.out.println(queue.take());

    }
}
