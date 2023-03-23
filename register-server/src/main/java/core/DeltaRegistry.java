package core;

import java.util.Queue;

import com.zhss.demo.register.server.core.ServiceRegistry.RecentlyChangedServiceInstance;

/**
 * 增量注册表
 * @author zhonghuashishan
 *
 */
public class DeltaRegistry {

	private Queue<RecentlyChangedServiceInstance> recentlyChangedQueue;
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue,
			Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}
	
	public Queue<RecentlyChangedServiceInstance> getRecentlyChangedQueue() {
		return recentlyChangedQueue;
	}
	public void setRecentlyChangedQueue(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue) {
		this.recentlyChangedQueue = recentlyChangedQueue;
	}
	public Long getServiceInstanceTotalCount() {
		return serviceInstanceTotalCount;
	}
	public void setServiceInstanceTotalCount(Long serviceInstanceTotalCount) {
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}
	
}
