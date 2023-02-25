package com.juc.concurrent;

public class InterruptDemo1 {
	
	public static void main(String[] args) throws Exception {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(!isInterrupted()) {  
					System.out.println("线程1在执行工作......");  
				}
			}
			
		};
		thread.start();

		// 主线程睡眠，而不是thread.sleep
		Thread.sleep(1000);  
		
		thread.interrupt();
	}
	
}
