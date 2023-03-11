package main.java.com.juc.reetrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentractLockDemo {

	static int data = 0;
	static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		new Thread() {
			
			public void run() {
				for(int i = 0; i < 10; i++) {
					lock.lock();
					//lock.tryLock(1000, TimeUnit.SECONDS);
					ReentractLockDemo.data++;
					System.out.println(ReentractLockDemo.data);  
					lock.unlock();
				}
			};
			
		}.start();
		
		new Thread() {
			
			public void run() {
				for(int i = 0; i < 10; i++) {
					lock.lock();
					ReentractLockDemo.data++;
					System.out.println(ReentractLockDemo.data);  
					lock.unlock();
				}
			};
			
		}.start();
	}
	
}
