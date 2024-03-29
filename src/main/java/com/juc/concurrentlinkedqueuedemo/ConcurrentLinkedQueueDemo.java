package main.java.com.juc.concurrentlinkedqueuedemo;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("张三");
        queue.offer("李四");
        queue.offer("王五");
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.remove("李四"));
        System.out.println(queue.size());
        System.out.println(queue.contains("张三"));

        Iterator<String> iterator = queue.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
