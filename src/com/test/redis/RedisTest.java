package com.test.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class RedisTest {
	
	private static int PORT = 6379; // 6379 redis port
	private static String HOST = "10.0.7.215"; // "10.0.7.239" redis host

	private static JedisPoolConfig config = null; // Jedis客户端池配置
	private static JedisPool pool = null; // Jedis客户端池
	private static Jedis j = null;

	static {
		config = new JedisPoolConfig();
		config.setMaxActive(60000);
		config.setMaxIdle(1000);
		config.setMaxWait(10000);
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, HOST, PORT, 100000);
	}

	public static void main(String[] args) {
		j = pool.getResource();
		Map<String, String> map = j.hgetAll("CELL");
		System.out.println(map);
		pool.returnResource(j);
	}
	
	public static void Test() {
		String filePath = "D:/wangyou/INI/td_adj.ini";
		BufferedReader reader = null;
		String strLine = null;
		
		j = pool.getResource();
		Pipeline pipe = j.pipelined();
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			while(null != (strLine = reader.readLine())) {
//				System.out.println(strLine);
//				pipe.rpush("gsmtest", strLine);
				pipe.lpush("gsmtest", strLine);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pipe.sync();
			pool.returnResource(j);
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
