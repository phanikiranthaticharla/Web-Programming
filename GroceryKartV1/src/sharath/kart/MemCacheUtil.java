package sharath.kart;
import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemCacheUtil {

	//   private static final Logger logger = LogManager.getLogger(MemCacheUtil.class);
	private static MemcachedClient client = null;

	static {

		try {
			client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean putInCache(String key, String jobListVO) {
		return client.set(key, 900,jobListVO) != null;
	}

	public static String getFromCache(String key) {
		//    return null;

		String value = (String) client.get(key);
		if (value == null) {
			//System.out.println("Cache miss for key:{}", key);
			return null;
		} else {
			//  System.out.println("Cache hit for key:{}", key);
			return value;
		}
	}
	public static boolean removeFromCache(String key) {
		//    return null;

		boolean value = client.delete(key) != null;
		return value;
	}

}