package com.reflect;

public class Demo {

	public String test(String[] strs, String name) {
		for (String string : strs) {
			System.out.printf("%s ", string);
		}
		System.out.println("----------");
		System.out.println(name);
		
		return "success";
	}
	
	public void say(String name) {
		System.out.println("hello " + name);
	}
}
