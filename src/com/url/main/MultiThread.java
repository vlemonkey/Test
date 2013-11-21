package com.url.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.url.util.HTMLUtils;

public class MultiThread {
	
	public static void main(String[] args) {
		String path = "D:\\hao123url\\one";
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			ExecutorService pool = Executors.newCachedThreadPool();
			Task task = null;
			for (File file2 : files) {
				task = new Task(file2.getAbsolutePath());
				pool.execute(task);
			}
			pool.shutdown();
			
		}
		
	}
	
	
	
	
}

class Task implements Runnable{
	
	private String filePath;

	public Task(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void run() {
		String strTemp;
		BufferedReader reader = null;
		
		FileWriter fw = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			fw = new FileWriter(filePath.concat("-1"));
			while(null != (strTemp = reader.readLine())) {
				if (StringUtils.isNotBlank(strTemp) &&
						StringUtils.startsWithIgnoreCase(strTemp, "http:")) {
//					fw.write(HTMLUtils.getContent(strTemp));
					fw.write(HTMLUtils.getHeader(strTemp));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
