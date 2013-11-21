package com.test.hubei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.Utils.FileUtil;

public class ReadFileTest {
	private final static Pattern pattern = Pattern.compile(",");
	private final static Pattern hostPattern = Pattern.compile("//([^/]+)/");
	private final static String filePath = "e:/work/hubei/cdr_10000/cdr_10000.csv";
	private final static int[] needIndex = {2, -1, 31, 32, 3, 19, 34, 35, 36, 37, 38, -1, 27, 13, 14, -1, 0, 1, -1, -1, 39};
	
	public static void main(String[] args) {
//		Test();
//		readFile(filePath, 10);
//		readFile(filePath);
		readCloumnFile(filePath, 0);
	}
	
	public static void Test() {
		String dataString = FileUtil.readOneLine(filePath, 3);
		System.out.printf("%s\n\n", dataString);
		
		String[] strDatas = pattern.split(dataString);
		for (String string : strDatas) {
			System.out.println(string);
		}
		System.out.println(strDatas.length);
	}
	
	/**
	 * 读取所有文件
	 * @param fileName
	 */
	public static void readCloumnFile(String fileName, int cloumn) {
		String strTemp;
		BufferedReader reader = null;
		Set<String> set = new HashSet<String>();
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while(null != (strTemp = reader.readLine())) {
				String[] strDatas = pattern.split(strTemp + ", ", 40);
				set.add(strDatas[cloumn]);
//				return;
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
			
			System.out.println(set);
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
				String[] strDatas = pattern.split(strTemp + ", ", 40);
				String temp = strDatas[39];
				Matcher matcher = hostPattern.matcher(temp);
				if (matcher.find()) {
					System.out.println(matcher.group(1));
				}
//				return;
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
	 * 读取文件前count条
	 * @param fileName
	 * @param count
	 */
	public static void readFile(String fileName, int count) {
		String strTemp;
		BufferedReader reader = null;
		int i = 0; // 初始化条数
		String needString = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while(null != (strTemp = reader.readLine())) {
				if (i++ < count) {
					String[] strDatas = pattern.split(strTemp, 40);
					for (int index : needIndex) {
						needString = index != -1 ? strDatas[index] : "";
						System.out.println(needString);
					}
					System.out.println("----------------------------");
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
}
