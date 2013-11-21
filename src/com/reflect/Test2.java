package com.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.test.Utils.ConfigUtils;
import com.test.Utils.JedisUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Test2 {
	private static Properties prop = ConfigUtils.getConfig("/config/reflect.properties");
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		String param = prop.getProperty("VALUEPARAM");
		String[]  strs = {"a", "b"};
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		Map<String, String> redisMap = JedisUtils.getResltMap("PAGE");
		map.put("page", redisMap);
		System.out.println(map.get("page").get("搜索法"));
		
		try {
			Class c = Class.forName(prop.getProperty("CLASS"));
//			Method[] methods = c.getDeclaredMethods();
//			for (Method method : methods) {
//				System.out.println(method.getName());
//				
//				Class[] cc = method.getParameterTypes();
//				for (Class class1 : cc) {
//					System.out.println(class1.getName());
//				}
//			}
			Method method = c.getMethod("run", String[].class, Map.class, String.class);
			Object str = method.invoke(c.newInstance(), strs, map, param);
			System.out.println(str);
			method = c.getMethod("filter", String[].class, Map.class, String.class);
			str = method.invoke(c.newInstance(), strs, map, param);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
