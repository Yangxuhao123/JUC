package main.java.com.juc.concurrenthashmapdemp;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("k1","v1");  //数组每个元素的分段加锁，保证写数据的线程安全性，数据不会错乱
        System.out.println(map.get("k1"));   //依赖于volatile读操作，保证你读到的是最新的数据结果，不加锁
        System.out.println(map.size());
    }
}
