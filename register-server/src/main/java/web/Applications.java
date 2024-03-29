package web;

import core.ServiceInstance;

import java.util.HashMap;
import java.util.Map;


/**
 * 完整的服务实例的信息
 * @author zhonghuashishan
 *
 */
public class Applications {

	private Map<String, Map<String, ServiceInstance>> registry
			= new HashMap<String, Map<String, ServiceInstance>>();

	public Applications() {
		
	}
	
	public Applications(Map<String, Map<String, ServiceInstance>> registry) {
		this.registry = registry;
	}

	public Map<String, Map<String, ServiceInstance>> getRegistry() {
		return registry;
	}
	public void setRegistry(Map<String, Map<String, ServiceInstance>> registry) {
		this.registry = registry;
	}
	
}
