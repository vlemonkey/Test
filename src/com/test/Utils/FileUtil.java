package com.test.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	/**
	 * 返回指定行
	 * @param fileName
	 * @return
	 */
	public static String readOneLine(String fileName, int count) {
		String strTemp = null;
		BufferedReader reader = null;
		int i = 0;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while(null != (strTemp = reader.readLine())) {
				if (i++ == count ) {
					return strTemp;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				reader.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			return strTemp;
		}
		return strTemp;
	}
	
	
	/**
	 * 读取文件前count条
	 * @param fileName
	 * @param count
	 */
	public static void readFile(String fileName, int count) {
		String strTemp;
		BufferedReader reader = null;
		int i = 0; // 初始化条数
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while(null != (strTemp = reader.readLine())) {
				if (i++ < count) {
					System.out.println(strTemp);
				}else {
					return;
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
	
	/**
	 * 读取所有文件
	 * @param fileName
	 */
	public static void readFile(String fileName) {
		String strTemp;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while(null != (strTemp = reader.readLine())) {
				System.out.println(strTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回bufferedreader
	 * @param fileName
	 * @return
	 */
	public static BufferedReader file2Reader(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			return reader;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				reader.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			return null;
		}
	}
}
