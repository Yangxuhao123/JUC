package main.java.com.juc.distributed;

/**
 * DataNode启动类
 *
 */
public class DataNode {

	/**
	 * 是否还在运行
	 */
	private volatile Boolean shouldRun;
	/**
	 * 负责跟一组NameNode通信的组件
	 */
	private main.java.com.juc.distributed.NameNodeOfferService offerService;
	
	/**
	 * 初始化DataNode
	 */
	private void initialize() {
		this.shouldRun = true;
		this.offerService = new main.java.com.juc.distributed.NameNodeOfferService();
		this.offerService.start();  
	}
	
	/**
	 * 运行DataNode
	 */
	private void run() {
		try {
			while(shouldRun) {
				Thread.sleep(10000);  
			}   
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataNode datanode = new DataNode();
		datanode.initialize();
		datanode.run(); 
	}
	
}
