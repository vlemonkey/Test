package com.test;

import org.apache.commons.lang.StringUtils;


public class IndexTest {
	
	public static void main(String[] args) {
		String s = "aa|bbbb|cccccc|dddddddd|eeeeeeeeee|ffffff";
//		for (int i=0; i<s.length(); i++) {
//			System.out.println(s.charAt(i) == '|');
//		}
		int index = test(s, '|', 3);
		System.out.println(index);
		System.out.println(StringUtils.substring(s, 0, index));
		System.out.println(StringUtils.substring(s, index));
	}

	public static int test(String str, char c, int number) {
		int count = 0;
		if (str.indexOf(c) > -1) {
			for (int i=0, n=str.length(); i<n; i++) {
				if (str.charAt(i) == c) {
					count++;
					if (count == number) {
						return ++i;
					}
				}
			}
		}
		return -1;
	}
}
