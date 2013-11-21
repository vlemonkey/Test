package com.test.readBinaryFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBinaryFileTest {

	private final static String fileName = "E:/work/hubei/data/data.dat";
	private final static int[] FIELD_LENGTH = {1,8,21,21,14,14,1,1,1,1,1,1,2,20,20,14,14,2,17,17,1,1,17,4,1,21,1,2,21,25};
	
	public static void main(String[] args) {
		readBinary();
	}
	
	/**
	 * 读取二进制文件
	 */
	public static void readBinary() {
		File file = new File(fileName);
		FileInputStream in = null;
		DataInputStream dis = null;
		if (file.exists()) {
			try {
				in = new FileInputStream(file);
				dis = new DataInputStream(in);
				
				byte[] buf = new byte[32];
				for (int i : FIELD_LENGTH) {
					dis.read(buf, 0, i);
					String recordType = new String(buf, "UTF-8");
					System.out.printf("%s ", recordType);
				}
				System.out.println();
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (null != dis) {
					try {
						dis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(null != in) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
