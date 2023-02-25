package com.juc.concurrent;

public class InterruptDemo2 {
	
	public static void main(String[] args) throws Exception {
		MyThread thread = new MyThread();
		thread.start();

		// 主线程睡眠1秒钟
		Thread.sleep(1000);  

		thread.setShouldRun(false);
		// 打断线程睡眠  会报异常  会被catch住
		// 下一轮while循环，shouldrun为false，就会退出while循环
		// 程序结束
		thread.interrupt();
	}
	
	private static class MyThread extends Thread {
		
		private Boolean shouldRun = true;
		
		@Override
		public void run() {
			while(shouldRun) {  
				try {
					System.out.println("线程1在执行工作......");  
					Thread.sleep(30 * 1000);  
				} catch (Exception e) {
					e.printStackTrace();     
				}
			}
		}
		
		public void setShouldRun(Boolean shouldRun) {
			this.shouldRun = shouldRun;
		}
		
	}
	
}
