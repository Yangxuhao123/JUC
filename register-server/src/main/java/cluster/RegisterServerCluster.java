package cluster;

import java.util.ArrayList;
import java.util.List;

public class RegisterServerCluster {

	private static List<String> peers = new ArrayList<String>();
	
	static {
		// 读取配置文件，看看你配合了哪些机器部署的register-server
	}
	
	public static List<String> getPeers() {
		return peers;
	}
	
}
