package main.java.com.juc.arrayblockingqueuedemo;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        queue.put("张三");
        System.out.println(queue.take());
        System.out.println(queue.size());
        queue.iterator();
    }
}
