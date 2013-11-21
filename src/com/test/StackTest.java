package com.test;

@SuppressWarnings({"unused"})
public class StackTest {
	private int x;
	private static int staticX;
	
	private String xx;
	private static String staticXx;

	
	public void testStack(int count) {
		String j = "";
		for (int i = 0; i < count; i++) {
			j += "1";
		}
//		System.out.println(j);
	}
	
	public void testInstance(int count) {
		for (int i = 0; i < count; i++) {
			xx += "1";
		}
//		System.out.println(x);
	}
	
	public void testStatic(int count) {
		for (int i = 0; i < count; i++) {
			staticXx += "1";
		}
//		System.out.println(staticX);
	}
	
	public static void main(String[] args) {
		StackTest t = new StackTest();
		long l = System.currentTimeMillis();
		t.testInstance(100000);
		long ll = System.currentTimeMillis();
		System.out.println(ll - l);
	}
}
