package com.test.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionsTest {
	public static void main(String[] args) {
		test();
//		arrayTest();
	}

	public static void test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "5");
		map.put("2", "4");
		map.put("3", "3");
		map.put("4", "2");
		map.put("5", "1");
		System.out.println(Collections.max( map.values()));
	}

	public static void arrayTest() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("zdd");
		al.add("ad");
		al.add("qsdw");
		al.add("vwerre");
		al.add("sxsd");
		System.out.println(Collections.max(al));
	}
}
