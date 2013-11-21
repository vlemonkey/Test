package com.url.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.url.util.HTMLUtils;

public class HeaderTest {
	public static void main(String[] args) {
		for (int i=0; i<1; i++) {
			getHeader(String.valueOf(i));
		}
	}

	public static void getHeader(String index) {
		String filePath = "D:\\hao123url\\one\\".concat(index);
		
		File file = new File(filePath);
		if (!file.isDirectory() && file.exists()) {
			String strTemp;
			BufferedReader reader = null;
			FileWriter fw = null;
			StringBuffer sb = new StringBuffer(1000);
			try {
				fw = new FileWriter(filePath.concat("-00"));
				reader = new BufferedReader(new FileReader(file));
				while (null != (strTemp = reader.readLine())) {
					sb.append(HTMLUtils.getHeader(strTemp));
				}
				fw.write(sb.toString());
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
