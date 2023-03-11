package main.java.com.juc.readwritelockdemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
        lock.readLock().unlock();

        lock.writeLock().lock();
        lock.writeLock().unlock();
    }
}
