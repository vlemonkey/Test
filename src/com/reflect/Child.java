package com.reflect;

import java.util.Map;


public class Child extends Parent{

	@Override
	public String run(String[] strs, Map<String, Map<String, String>> map, String paramters) {
		System.out.println(strs[0]);
		System.out.println(paramters);
		System.out.println("-----------------");
		return "SUCCESS";
	}

	@Override
	public boolean filter(String[] strs, Map<String, Map<String, String>> map, String paramters) {
		System.out.println(strs[0]);
		System.out.println(paramters);
		System.out.println("-----------------");
		return true;
	}

}
