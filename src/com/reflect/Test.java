package com.reflect;

import java.lang.reflect.Method;

@SuppressWarnings("rawtypes")
public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		Class c = Class.forName("com.reflect.Demo");
		
		Method method = c.getMethod("test", String[].class, String.class);
		String[] strs = {"aa","bb","cc"};
		String s = (method.invoke(c.newInstance(), strs, "zhangsan")).toString();
		System.out.println(s);
		
		
		// 获取所有方法名
//		Method[] methods = c.getDeclaredMethods();
//		for (Method method : methods) {
//			System.out.println(method.getDeclaringClass());
//			System.out.println("method name:" + method.getName());
//			System.out.println("return type:" + method.getReturnType());
//			
//			Class[] pvec = method.getParameterTypes();
//			for (Class class1 : pvec) {
//				System.out.println(class1);
//			}
//			
//			System.out.println("--------------");
//		}
	}
}
