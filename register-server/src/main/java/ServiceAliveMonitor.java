import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微服务存活状态监控组件
 * @author zhonghuashishan
 *
 */
public class ServiceAliveMonitor {
	
	/**
	 * 检查服务实例是否存活的间隔
	 */
	private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

	/**
	 * 负责监控微服务存活状态的后台线程
	 */
	private Daemon daemon;
	
	public ServiceAliveMonitor() {
//		ThreadGroup daemonThreadGroup = new ThreadGroup("daemon"); 
//		System.out.println("daemon线程组的父线程组是：" + daemonThreadGroup.getParent()); 
//		this.daemon = new Daemon(daemonThreadGroup, "ServiceAliveMonitor");   
		
		this.daemon = new Daemon();
		// 只要设置了这个标志位，就代表这个线程是一个daemon线程，后台线程
		// 非daemon线程，我们一般叫做工作线程
		// 如果工作线程（main线程）都结束了，daemon线程是不会阻止jvm进程退出的
		// daemon线程会跟着jvm进程一起退出
		daemon.setDaemon(true);  
		daemon.setName("ServiceAliveMonitor");  
	}
	
	/**
	 * 启动后台线程
	 */
	public void start() {
		daemon.start();
	}

	/**
	 * 负责监控微服务存活状态的后台线程
	 * @author zhonghuashishan
	 *
	 */
	private class Daemon extends Thread {
		
		private ServiceRegistry registry = ServiceRegistry.getInstance();
		
//		public Daemon(ThreadGroup threadGroup, String name) {
//			super(threadGroup, name);
//		}
		
		@Override
		public void run() {
			Map<String, Map<String, ServiceInstance>> registryMap = null;
			
//			System.out.println(Thread.currentThread().getName() + "线程的线程组是：" 
//					+ Thread.currentThread().getThreadGroup());  
			
			while(true) {
				try {
					// 可以判断一下是否要开启自我保护机制
					SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
					if(selfProtectionPolicy.isEnable()) {
						Thread.sleep(CHECK_ALIVE_INTERVAL);
						continue;
					}
					
					// 定义要删除的服务实例的集合
					List<ServiceInstance> removingServiceInstances = new ArrayList<ServiceInstance>();
					
					// 开始读服务注册表的数据，这个过程中，别人可以读，但是不可以写
					try {
						// 对整个服务注册表，加读锁
						registry.readLock();
						
						registryMap = registry.getRegistry();
						for(String serviceName : registryMap.keySet()) {
							Map<String, ServiceInstance> serviceInstanceMap = 
									registryMap.get(serviceName);
							for(ServiceInstance serviceInstance : serviceInstanceMap.values()) {
								// 说明服务实例距离上一次发送心跳已经超过90秒了
								// 认为这个服务就死了
								// 从注册表中摘除这个服务实例
								if(!serviceInstance.isAlive()) {
									removingServiceInstances.add(serviceInstance);
								}  
							}
						}
					} finally {
						registry.readUnlock();
					}
					
					// 将所有的要删除的服务实例，从服务注册表删除
					for(ServiceInstance serviceInstance : removingServiceInstances) {
						registry.remove(serviceInstance.getServiceName(), 
								serviceInstance.getServiceInstanceId()); 
						
						// 更新自我保护机制的阈值
						synchronized(SelfProtectionPolicy.class) {
							selfProtectionPolicy.setExpectedHeartbeatRate(
									selfProtectionPolicy.getExpectedHeartbeatRate() - 2);  
							selfProtectionPolicy.setExpectedHeartbeatThreshold(
									(long)(selfProtectionPolicy.getExpectedHeartbeatRate() * 0.85));  
						}
					}
					
					// 过期注册表缓存
					if(removingServiceInstances.size() != 0) {
						// 过期掉注册表缓存
						ServiceRegistryCache.getInstance().invalidate();
					}  
					
					Thread.sleep(CHECK_ALIVE_INTERVAL);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
