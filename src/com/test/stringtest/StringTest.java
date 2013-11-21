package com.test.stringtest;

public class StringTest {
	public static void main(String[] args) {
		int count = 1000000;
		long l = System.currentTimeMillis();
		buildTest(count);
		long ll = System.currentTimeMillis();
		System.out.println("builder:" + (ll - l));
		bufferTest(count);
		System.out.println("buffer:" + (System.currentTimeMillis() - ll));
	}

	public static void buildTest(int count) {
		StringBuilder sBuilder = new StringBuilder(100000);
		for(int i=0; i<count; i++) {
			sBuilder.append(i);
		}
	}
	
	public static void bufferTest(int count) {
		StringBuffer sBuilder = new StringBuffer(100000);
		for(int i=0; i<count; i++) {
			sBuilder.append(i);
		}
	}
}
