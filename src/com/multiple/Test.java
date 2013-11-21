package com.multiple;

import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	
	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		
		try {
			queue.put("123");
			System.out.println(queue.poll());
			System.out.println(queue.poll());
			queue.put("456");
			queue.put("789");
			System.out.println(queue.poll());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
