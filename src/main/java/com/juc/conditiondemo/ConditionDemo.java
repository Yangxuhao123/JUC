package main.java.com.juc.conditiondemo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    static int data = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            public void run() {
                lock.lock();
                System.out.println("第一个线程加锁");
                try {
                    System.out.println("第一个线程释放锁以及阻塞等待");
                    condition.await();
                    System.out.println("第一个线程重新获取到锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("第一个线程释放锁");
                lock.unlock();
            }

            ;

        }.start();

        Thread.sleep(3000);

        new Thread() {
            public void run() {
                lock.lock();
                System.out.println("第二个线程加锁");
                System.out.println("第二个线程唤醒第一个线程");
                condition.signal();
                System.out.println("第二个线程释放锁");
                lock.unlock();
            }
        }.start();
    }
}
