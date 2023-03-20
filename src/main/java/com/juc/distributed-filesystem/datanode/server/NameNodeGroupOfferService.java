package main.java.com.juc.distributed;

import java.util.concurrent.CountDownLatch;

/**
 * 负责跟一组NameNode进行通信的OfferServie组件
 * @author zhonghuashishan
 *
 */
public class NameNodeGroupOfferService {

	/**
	 * 负责跟NameNode主节点通信的ServiceActor组件
	 */
	private main.java.com.juc.distributed.NameNodeServiceActor activeServiceActor;
	/**
	 * 负责跟NameNode备节点通信的ServiceActor组件
	 */
	private main.java.com.juc.distributed.NameNodeServiceActor standbyServiceActor;
	
	/**
	 * 构造函数
	 */
	public NameNodeGroupOfferService() {
		this.activeServiceActor = new main.java.com.juc.distributed.NameNodeServiceActor();
		this.standbyServiceActor = new main.java.com.juc.distributed.NameNodeServiceActor();
	}
	
	/**
	 * 启动OfferService组件
	 */
	public void start() {
		// 直接使用两个ServiceActor组件分别向主备两个NameNode节点进行注册
		register();
	}
	
	/**
	 * 向主备两个NameNode节点进行注册
	 */
	private void register() {
		try {
			CountDownLatch latch = new CountDownLatch(2);  
			this.activeServiceActor.register(latch); 
			this.standbyServiceActor.register(latch); 
			latch.await();
			System.out.println("主备NameNode全部注册完毕......");   
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}
	  
}
