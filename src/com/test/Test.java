package com.test;







public class Test {
	public static void main(String[] args) {
		open();
	}
	
	public static void open() {
		boolean isOpen = false;  
		assert isOpen=true; //如果开启了断言，会将isOpen的值改为true 
		System.out.println(isOpen);//打印是否开启了断言   
	}
}
