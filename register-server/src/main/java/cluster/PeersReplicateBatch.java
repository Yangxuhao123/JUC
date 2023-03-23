package cluster;

import java.util.ArrayList;
import java.util.List;

import com.zhss.demo.register.server.web.AbstractRequest;

/**
 * 集群同步batch
 * @author zhonghuashishan
 *
 */
public class PeersReplicateBatch {

	private List<AbstractRequest> requests = new ArrayList<AbstractRequest>();
	
	public void add(AbstractRequest request) {
		this.requests.add(request);
	}

	public List<AbstractRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<AbstractRequest> requests) {
		this.requests = requests;
	}
	
}
