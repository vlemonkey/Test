package com.test.makedata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WordCountData {
	
	private static final String PATH = "/home/boco/#1/#2.txt";
	
	private static final String STRDAY = "20130812";         // 修改这个时间

	public static void main(String[] args) {
		
		int count =5;
		
		String[] strs = new String[count];
		for(int i=0; i<count; i++) {
			strs[i] = PATH.replace("#1", STRDAY).replace("#2", String.valueOf(i));
		}
		
		for (String filePath : strs) {
			new Thread(new FThread1(filePath, Integer.parseInt(args[0]))).start();
		}
		
	}
}

class FThread1 implements Runnable{
	
	private static final String STRDAY = "20130902";           // 修改这个时间
	BufferedWriter bWriter; 
	private int count;
	
	public FThread1(String fileName, int count) {
		this.count = count;
		File file = new File(fileName);
		
		File fileParent = new File(file.getParent());
		if (!fileParent.exists()) {
			fileParent.mkdir();
		}
		
		try {
			bWriter = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		try {
			for (int i=0; i<count; i++) {
				bWriter.write(MakeData.getRandomData(STRDAY));
				bWriter.newLine();
			}
			bWriter.flush();
			System.out.printf("%s finished.\n", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
