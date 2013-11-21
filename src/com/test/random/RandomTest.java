package com.test.random;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unused")
public class RandomTest {
	public static void main(String[] args) {
		randomTest(500);
	}
	
	public static void randomTest(int count) {
		Random random = new Random();
		String s = StringUtils.leftPad(String.valueOf(random.nextInt(1000000000)), 
				10, "0");
		System.out.println(s);
		long l = System.currentTimeMillis();
//		for(int i=0; i<100000000; i++) {
//			random.nextInt(count);
////			System.out.println(random.nextInt(count));
//		}
//		System.out.println(System.currentTimeMillis() - l);
	}
}
