package com.test.stringtest;


@SuppressWarnings({"unused"})
public class StringTest2 {
	
	public static void main(String[] args) {
		long count = 100000;
		long l = System.currentTimeMillis();
		test(count);
		long ll = System.currentTimeMillis();
		test2(count);
		System.out.printf("used:%s\n", ll-l);
		System.out.printf("used:%s\n", System.currentTimeMillis() - ll);
	}

	public static void test(long count) {
		String str = null;
		for (long i = 0; i < count; i++) {
			str = i + "";
		}
	}
	
	public static void test2(long count) {
		for (long i = 0; i < count; i++) {
			String str = i + "";
		}
	}
}
