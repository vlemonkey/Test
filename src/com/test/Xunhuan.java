package com.test;

@SuppressWarnings({"unused"})
public class Xunhuan {
	
	public void asc(int count) {
		int j = 0;
		for (int i = 0; i < count; i++) {
			j += 1;
		}
	}
	
	public void desc(int count) {
		int j = 0;
		for (int i = count; i > 0; i--) {
			j += 1;
		}
	}
	
	public static void main(String[] args) {
		Xunhuan xh = new Xunhuan();
		long l = System.currentTimeMillis();
		xh.desc(1000000000);
//		xh.asc(1000000000);
		long ll = System.currentTimeMillis();
		System.out.println(ll - l);
	}
}
