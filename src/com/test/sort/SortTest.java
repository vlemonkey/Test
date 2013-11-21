package com.test.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SortTest {
	public static void main(String[] args) {
		testSortList();
	}
	

	public static void testSortList() {
		List<String> testList = new ArrayList<String>();
		testList.add("4");
		testList.add("3");
		testList.add("2");
		testList.add("7");
		testList.add("6");
		testList.add("5");
		
		List<String> list = new ArrayList<String>();
		for (String value : testList) {
			list.add(value);
		}
		Collections.sort(list, Collections.reverseOrder());
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0, n=list.size(); i < n; i++) {
			map.put(list.get(i), String.valueOf(i+1));
		}
		
		for (String str : testList) {
			System.out.println(map.get(str));
		}
		
//		for (String string : list) {
//			System.out.println(string);
//		}
	}
}
