package com.test.taobao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Snippet {
	
	public static void main(String[] args) {
		String fileName = "D:\\taobao1111\\cdr_144_13111139.txt";
		readFile(fileName);
	}

	/**
	 * 读取所有文件
	 * 
	 * @param fileName
	 */
	public static void readFile(String fileName) {
		String strTemp;
		BufferedReader reader = null;
		
		String[] strs = null;
		Set<String> set = new HashSet<String>();
		int count = 0;
		int total = 0;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while (null != (strTemp = reader.readLine())) {
//				System.out.println(strTemp);
				strs = strTemp.split(",");
				total++;
				if (strs.length>40) {
					if (strs[39].contains("taobao") || strs[39].contains(".tmall.")) {
						System.out.println(strs[39]);
						count ++;
					}
				}
			}
			
			System.out.println("taobao:" + count);
			System.out.println("total:" + total);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
