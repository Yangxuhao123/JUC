package main.java.com.juc.distributed;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 负责跟一组NameNode进行通信的OfferServie组件
 * @author zhonghuashishan
 *
 */
public class NameNodeOfferService {

	/**
	 * 负责跟NameNode主节点通信的ServiceActor组件
	 */
	private main.java.com.juc.distributed.NameNodeServiceActor activeServiceActor;
	/**
	 * 负责跟NameNode备节点通信的ServiceActor组件
	 */
	private main.java.com.juc.distributed.NameNodeServiceActor standbyServiceActor;
	/**
	 * 这个datanode上保存的ServiceActor列表
	 */
	private CopyOnWriteArrayList<main.java.com.juc.distributed.NameNodeServiceActor> serviceActors;
	
	/**
	 * 构造函数
	 */
	public NameNodeOfferService() {
		this.activeServiceActor = new main.java.com.juc.distributed.NameNodeServiceActor();
		this.standbyServiceActor = new main.java.com.juc.distributed.NameNodeServiceActor();
		
		this.serviceActors = new CopyOnWriteArrayList<main.java.com.juc.distributed.NameNodeServiceActor>();
		this.serviceActors.add(activeServiceActor);
		this.serviceActors.add(standbyServiceActor);
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
	
	/**
	 * 关闭指定的一个ServiceActor
	 * @param serviceActor
	 */
	public void shutdown(main.java.com.juc.distributed.NameNodeServiceActor serviceActor) {
		this.serviceActors.remove(serviceActor);
	}
	
	/**
	 * 迭代遍历ServiceActor
	 */
	public void iterateServiceActors() {
		Iterator<main.java.com.juc.distributed.NameNodeServiceActor> iterator = serviceActors.iterator();
		while(iterator.hasNext()) {
			iterator.next();
		}
	}
	  
}
