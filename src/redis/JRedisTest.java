package redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JRedisTest {
	
	private JedisPool jedisPool;
	private ShardedJedisPool shardedJedisPool;

	 /**
     * 初始化非切片池1111111111
     */
	private void initialPool(){
		 // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        jedisPool = new JedisPool(config,"127.0.0.1",6379);
	}
//	
//	/** 
//     * 初始化切片池 
//     */ 
    private void initialShardedPool(){
    	 // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxTotal(20); 
        config.setMaxIdle(5); 
        config.setMaxWaitMillis(1000l); 
        config.setTestOnBorrow(false); 
        
     // slave链接 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo("192.168.81.129", 7000, "node0")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7001, "node1")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7002, "node2")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7003, "node3")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7004, "node4")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7005, "node5")); 
        shards.add(new JedisShardInfo("192.168.81.129", 7006, "node6")); 
        // 构造池 
        shardedJedisPool = new ShardedJedisPool(config,shards);
    }

    public void Close() {     
    	shardedJedisPool.destroy();
    	jedisPool.destroy();
    }
    
    public Jedis getJedis() {
        return jedisPool.getResource(); 
    }

    public ShardedJedis getShardedJedis() {
        return  shardedJedisPool.getResource();
    }
    
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		Jedis jedis = new Jedis("192.168.81.129",7000);
//		jedis.set("1", "111111");
////		System.out.println(jedis.exists("2".getBytes()));
//		System.out.println(jedis.get("1"));
//		System.out.println(jedis.ping());
		
		JRedisTest test = new JRedisTest();
//		test.initialPool();
//		Jedis jedis = test.getJedis();
		test.initialShardedPool();
		ShardedJedis jedis = test.getShardedJedis();
		System.out.println(jedis.exists("2"));  //判断key是否存在
//		jedis.set("abc1111","abc111111111111");
//		jedis.set("abc2222","abc222222222222");
//		jedis.set("abac3333","abc333333333332");
//		jedis.set("abc1","abc1");      //设置key-value
//		jedis.del("abac3333","abc1");  //删除对应的key值
//		jedis.expire("1", 4);
//		for(String key : jedis.keys("*")){  //遍历全部的key-value
//			System.out.println(key+"="+new String(jedis.get(key.getBytes())));
//		}
//		Thread.sleep(2000);
//		
//		System.out.println("-------jedis.ttl(1)--------="+jedis.ttl("1"));
//		
//		Thread.sleep(2000);
//		
		long a = System.currentTimeMillis();
//		for(String key : jedis.keys("*")){  //遍历全部的key-value
//			System.out.println(key+"="+new String(jedis.get(key)));
//		}
//		
//		System.out.println(jedis.keys("*").size());
//		jedis.flushDB();
//		System.out.println(jedis.keys("*").size());
//		jedis.flushAll();
//		for(int i=0;i<1000000;i++){
//			jedis.set(i+"abc", "valueaaaaaaaaaaaa"+i);
//		}
//		jedis.set("abc1111","abc");
//		System.out.println(new String(jedis.get("abc1111")));
//		jedis.append("abc1111","111111111111");
//		System.out.println(new String(jedis.get("abc1111")));
//		jedis.mset("1","value1","2","value2","3","value3","4","value5");
//		System.out.println(jedis.mget("1","3")); 
//		jedis.setnx("1", "valuenx");
//		jedis.setnx("5", "5555555");
//		System.out.println(jedis.mget("1","3","5"));
//		jedis.hset("user", "username", "jiandong");
//		System.out.println(jedis.hget("user", "username"));
//		jedis.lpush("list", "222");
//		System.out.println(jedis.lrange("list", 0, 1));
//		for(String key : jedis.keys("*")){  //遍历全部的key-value
//			System.out.println(key+"="+jedis.get(key));
//		}
		System.out.println(System.currentTimeMillis() - a);
		
//		jedis.watch("1","5");
//		jedis.multi();
//		jedis.set("1111111", "1");
//		jedis.del("1");
//		System.out.println(jedis.info());
		
	}
	
	
	

}
