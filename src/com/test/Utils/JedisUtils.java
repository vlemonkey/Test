package com.test.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import com.test.Utils.ConfigUtils;

public class JedisUtils {
	private static final String CONFIG_PATH = "/config/redis.properties";
	private static String env;
	private static String host;
	private static int port;
	private static JedisPoolConfig conf = null;
	private static Properties prop;
	private static JedisPool pool;
	private static final String TABLE_DICT;
	private static final String TABLE_PAGE;
	static {
		prop = ConfigUtils.getConfig(CONFIG_PATH);
		env = prop.getProperty("ENV", "");
		TABLE_DICT = prop.getProperty("REDIS.TABLE.DICT");
		TABLE_PAGE = prop.getProperty("REDIS.TABLE.PAGE");
	}

	private static void initPool() {
		conf = new JedisPoolConfig();
		conf.setMaxActive(1000);
		conf.setMaxIdle(500);
		conf.setMaxWait(10000);
		conf.setTestOnBorrow(true);
		host = prop.getProperty(env.concat(".REDIS.HOST"), "");
		port = Integer.parseInt(prop.getProperty(env.concat(".REDIS.PORT"), ""));
		pool = new JedisPool(conf, host, port, 60 * 1000);
	}
	
	private static Jedis getJedis() {
		if (null == pool) {
			initPool();
		}
		return pool.getResource();
	}

	private static void returnJedis(Jedis jedis) {
		if (jedis != null) {
			pool.returnResource(jedis);
		}
	}
   
	public static void initRedisTable(String fileName, String tableName, boolean sjipFirst) {
		File file = new File(fileName);
		BufferedReader in = null;
		Jedis jedis = null;
		Pipeline pipe = null;
		try {
			in = new BufferedReader(new FileReader(file));
			jedis = getJedis();
			pipe = jedis.pipelined();
			if (jedis.exists(tableName)) {
				jedis.del(tableName);
			}
			String line;
			int count = 1;
			while ((line = in.readLine()) != null) {
				if (sjipFirst) {
					sjipFirst = false;
					continue;
				}
				String phoneNo = 86 + line.split(",")[0];
				pipe.sadd(tableName, phoneNo);
//				pipe.lpush(tableName, phoneNo);
				count++;
			}
			pipe.sync();
			returnJedis(jedis);
			System.out.printf("finish init:%s\n", tableName + " : " + count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeRedis(Jedis jedis) {
		if (jedis != null) {
			jedis.disconnect();
			pool.destroy();
		}
		conf = null;
	}

	public static void cleanTable() {
		delTable(TABLE_DICT);
	}
	
	public static void cleanPageTable() {
		delTable(TABLE_PAGE);
	}
	
	public static void delTable(String tableName) {
		Jedis j = getJedis();
		if (j.exists(tableName)) {
			j.del(tableName);
		}
		returnJedis(j);
	}
	
	public static void putOneHash2Redis(String tableName,
			String key, String value) {
		Jedis j = getJedis();
		j.hset(tableName, key, value);
		returnJedis(j);
	}

	public static void putOneData2RedisLb(String key, String value) {
		Jedis jedis = getJedis();
		jedis.set(key, value);
		returnJedis(jedis);
	}

	public static String getOneData2RedisLb(String key) {
		Jedis jedis = getJedis();
		String s = jedis.get(key);
		returnJedis(jedis);
		return s;
	}
	
	/**
	 * 默认表  PAGE
	 * @param totalMap
	 */
	public static void putpageInfo2Redis(Map<String, String> totalMap) {
		putData2Redis(TABLE_PAGE, totalMap);
	}
	
	/**
	 * 默认表  PAGE
	 * @return
	 */
	public static Map<String, String> getPageResult() {
		return getResltMap(TABLE_PAGE);
	}
	
	
	/**
	 * 数据导入默认的表
	 * @param totalMap
	 */
	public static void putData2Redis(Map<String, String> totalMap) {
		putData2Redis(TABLE_DICT, totalMap);
	}
	
	/**
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getResltMap() {
		return getResltMap(TABLE_DICT);
	}
	
	
	/**
	 * 批量人 redis
	 * 
	 * @param tableName
	 * @param totalMap
	 */
	public static void putData2Redis(String tableName, Map<String, String> totalMap) {
		Jedis j = getJedis();
		j.hmset(tableName, totalMap);
		returnJedis(j);
	}
	/**
	 * 根据tableName返回对应的map
	 * 
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getResltMap(String tableName) {
		Jedis j = getJedis();
		Map<String, String> rsMap = j.hgetAll(tableName);
		returnJedis(j);
		return null != rsMap ? rsMap : new HashMap<String, String>();
	}
	
	/**
	 * 根据tableName返回list
	 * @param tableName
	 * @return
	 */
	public static List<String> findList(String tableName){
		Jedis j = getJedis();
		List<String> list = j.lrange(tableName, 0, -1);
		returnJedis(j);
		return null != list ? list : new ArrayList<String>();
	}
	
	/**
	 * 根据tableName返回set
	 * @param tableName
	 * @return
	 */
	public static Set<String> findSet(String tableName) {
		Jedis j = getJedis();
		Set<String> set = j.smembers(tableName);
		returnJedis(j);
		return null == set ? new HashSet<String>() : set;
	}
	public static void main(String[] args) {
		initRedisTable("F:/metro_user_result.csv", "CDRURL", false);
		System.out.println(findSet("CDRURL").size());
	}
}
