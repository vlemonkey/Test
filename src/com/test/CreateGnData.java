package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Pattern;

public class CreateGnData {

	private static final int FIVE_MINUTE = 300;
	private static final String READ_FILE_PATH = "E:\\work\\boco\\DataPlatform\\GN\\2013032016011988000027073.txt\\2013032016011988000027073.txt";
//	private static final String READ_FILE_PATH = "E:/test/test.txt";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
	private static final Pattern pDate = Pattern.compile("[^\\d]");
	private static final Pattern pRepalceDate = Pattern.compile("\\d{4}-[^\\|]*\\d{7}");
	
	private static final String WRITE_PATH = "E:/GNDATA/#1.txt";

	private static String writeFilePath;
	private static Calendar c = getStartTime();
	private static Random random  = new Random();
	
	
	
	public static void writeMultiFiles() {
		String strOld = null;
		String strNew = null;
		BufferedReader reader = null;
		FileWriter fw = null;
		 
		int count = 48;
		while(count > 0) {
			writeFilePath = getWriteFilePath(c);
			
			System.out.printf("开始写入第%d个文件:%s\n", (49-count), writeFilePath);
			
			try {
				fw = new FileWriter(new File(writeFilePath));
				reader = new BufferedReader(new FileReader(new File(READ_FILE_PATH)));
				while(null != (strOld = reader.readLine())) {
//					System.out.printf("old: %s\n", strOld);
					strNew = pRepalceDate.matcher(strOld).replaceFirst(getRandomTime(c));
//					System.out.printf("new: %s\n", strNew);
					fw.write(strNew + "\n");
				}
			} catch (Exception e) {
				System.out.println("读写文件错误");
				e.printStackTrace();
			}finally {
				try {
					reader.close();
					fw.close();
				} catch (IOException e1) {
					System.out.println("关闭时出错");
					e1.printStackTrace();
				}
			}
			
			System.out.printf("此文件写入完毕\n\n");
			getNextFiveMinute(c);
			count--;
		}
		
	}
	
	// 开始时间
	private static Calendar getStartTime() {
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, 2013);
		c.set(Calendar.MONTH,4);
		c.set(Calendar.DAY_OF_MONTH, 8);
		c.set(Calendar.HOUR, 22);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	// 返回5分钟的随机毫秒数
	private static int getRandomMinute() {
		return random.nextInt(FIVE_MINUTE);
	}
	
	/**
	 * 随机
	 * @param c
	 * @param randomMillisecond
	 * @return
	 */
	private static String getRandomTime(Calendar c) {
		int randomSecond = getRandomMinute();
		return getRandomTime(c, randomSecond);
	}
	
	/**
	 * 随机
	 * @param c
	 * @param randomMillisecond
	 * @return
	 */
	private static String getRandomTime(Calendar c, int randomSecond) {
		Calendar c2 = GregorianCalendar.getInstance();
		c2.setTime(c.getTime());
		c2.add(Calendar.SECOND, randomSecond);
		return date2String(c2);
	}
	
	/**
	 * 日期转字符串
	 * @param c
	 * @return
	 */
	private static String date2String(Calendar c) {
		Date date = c.getTime();
		return date2String(date);
	}
	
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	private static String date2String(Date date) {
		return dateFormat.format(date);
	}
	
	// 获取下一个5分钟时间 
	private static void getNextFiveMinute(Calendar c) {
		c.add(Calendar.MINUTE, 5);
		c.set(Calendar.MILLISECOND, 0);
	}
	
	private static String getWriteFilePath(Calendar c) {
		String strDate = date2String(c);
		String fileName = pDate.matcher(strDate).replaceAll("").substring(0,12);
		return WRITE_PATH.replace("#1", fileName);
	}
	
	public static void main(String[] args) {
		writeMultiFiles();
		
//		Calendar c = getStartTime();
//		System.out.println(c.getTime());
//		System.out.println(getRandomTime(c, getRandomMinute()));
//		getNextFiveMinute(c);
//		System.out.println(date2String(c));
//		getNextFiveMinute(c);
//		System.out.println(date2String(c));
//		getNextFiveMinute(c);
//		System.out.println(date2String(c));
//		System.out.println(getWriteFilePath(c));
	}
	
}
