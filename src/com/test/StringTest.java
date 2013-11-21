package com.test;

@SuppressWarnings("unused")
public class StringTest {
	
	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		test2(10000000000L);
		long ll = System.currentTimeMillis();
		System.out.println(ll - l);
		test(10000000000L);
		System.out.println(System.currentTimeMillis() - ll);
	}

	public static void test(long count) {
		long str;
		for (long i = 0; i < count; i++) {
			str = i;
		}
	}
	
	public static void test2(long count) {
		for (long i = 0; i < count; i++) {
			long str = i;
		}
	}
}
