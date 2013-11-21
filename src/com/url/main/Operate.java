package com.url.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang.StringUtils;

import com.url.util.HTMLUtils;

public class Operate {
	
	public static void main(String[] args) {
		String folder = "D:\\hao123url\\one-header-final";
		renameFiles(folder);
//		readFolderHeader(folder);
		
//		String folder = "D:\\hao123url\\one";
//		readFolder(folder);
		
//		String file = "D:\\hao123url\\one\\14";
//		readFile(file, file+"-1");
	}
	
	/**
	 * 读取filepath中的url链接
	 * 读取链接中的内容写入到文件targetPath中
	 * @param filePath
	 * @param targetPath
	 */
	private static void readFileHeader(String filePath, String targetPath) {
		File file = new File(filePath);
		if (!file.isDirectory() && file.exists()) {
			String strTemp;
			BufferedReader reader = null;
			FileWriter fw = null;
			try {
				fw = new FileWriter(targetPath);
				reader = new BufferedReader(new FileReader(file));
				while (null != (strTemp = reader.readLine())) {
					fw.write(HTMLUtils.getHeader(strTemp));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (null != fw) {
					try {
						fw.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 读取目录
	 * @param folderPath
	 */
	public static void renameFiles(String folderPath) {
		File folder = new File(folderPath);
		if (folder.isDirectory()) {
			int count = 0;
			String newFileName;
			File[] files = folder.listFiles();
			for (File file : files) {
				System.out.println(count + "=" + StringUtils.substringBefore(file.getName(), "."));
//				newFileName = file.getAbsolutePath().replace(file.getName(), String.valueOf(count++));
				newFileName = file.getAbsolutePath().replace("-00", "");
				file.renameTo(new File(newFileName));
			}
		}
		
	}
	
	
	/**
	 * 读取目录
	 * @param folderPath
	 */
	public static void readFolder(String folderPath) {
		String filePath;
		File folder = new File(folderPath);
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				System.out.println("file name:" + file.getAbsolutePath());
				filePath = file.getPath();
				readFile(filePath, filePath.concat("-1"));
			}
		}
		
	}
	
	/**
	 * 读取目录
	 * @param folderPath
	 */
	public static void readFolderHeader(String folderPath) {
		String filePath;
		File folder = new File(folderPath);
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				System.out.println("file name:" + file.getAbsolutePath());
				filePath = file.getPath();
				readFileHeader(filePath, filePath.concat("-1"));
			}
		}
		
	}

	/**
	 * 读取filepath中的url链接
	 * 读取链接中的内容写入到文件targetPath中
	 * @param filePath
	 * @param targetPath
	 */
	public static void readFile(String filePath, String targetPath) {
		File file = new File(filePath);
		if (!file.isDirectory() && file.exists()) {
			String strTemp;
			BufferedReader reader = null;
			FileWriter fw = null;
			try {
				fw = new FileWriter(targetPath);
				reader = new BufferedReader(new FileReader(file));
				while (null != (strTemp = reader.readLine())) {
					fw.write(HTMLUtils.getContent(strTemp));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (null != fw) {
					try {
						fw.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
	}
}
