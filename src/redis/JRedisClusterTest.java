package redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class JRedisClusterTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JedisCluster jedisCluster = (JedisCluster) context.getBean("jedisCluster");
//		System.out.println(jedisCluster.get("1"));
//		System.out.println(jedisCluster.ttl("1"));
//		System.out.println(jedisCluster.del("1"));
//		System.out.println(jedisCluster.incr("1"));
//		System.out.println(jedisCluster.get("1"));
//		
		long a = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
//			jedisCluster.set(i+"", "aaaa"+i);
			System.out.println(jedisCluster.get(i+""));
//			jedisCluster.del(i+"");
		}
		System.out.println("use time : " + (System.currentTimeMillis()-a));
	}

}
