package com.juc.RegisterClient;

/**
 * 心跳请求
 * @author zhonghuashishan
 *
 */
public class HeartbeatRequest {

	/**
	 * 服务实例id
	 */
	private String serviceInstanceId;

	public String getServiceInstanceId() {
		return serviceInstanceId;
	}
	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}
	
}
