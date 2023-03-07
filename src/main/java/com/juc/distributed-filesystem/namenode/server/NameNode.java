package com.juc.distributed;

/**
 * NameNode核心启动类
 * @author zhonghuashishan
 *
 */
public class NameNode {

	/**
	 * NameNode是否在运行
	 */
	private volatile Boolean shouldRun;
	/**
	 * 负责管理元数据的核心组件
	 */
	private com.juc.distributed.FSNamesystem namesystem;
	/**
	 * NameNode对外提供rpc接口的server，可以响应请求
	 */
	private com.juc.distributed.NameNodeRpcServer rpcServer;
	
	public NameNode() {
		this.shouldRun = true;
	}
	
	/**
	 * 初始化NameNode
	 */
	private void initialize() {
		this.namesystem = new com.juc.distributed.FSNamesystem();
		this.rpcServer = new com.juc.distributed.NameNodeRpcServer(this.namesystem);
		this.rpcServer.start();
	}
	
	/**
	 * 让NameNode运行起来
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
		
	public static void main(String[] args) throws Exception {		
		NameNode namenode = new NameNode();
		namenode.initialize();
		namenode.run();
	}
	
}
