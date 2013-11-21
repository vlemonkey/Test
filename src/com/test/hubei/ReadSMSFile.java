package com.test.hubei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ReadSMSFile {

	private static Pattern pattern = Pattern.compile(",");
	
	public static void main(String[] args) {
		read();
	}
	
	public static void read() {
		String moFileName = "E:\\work\\hubei\\sms\\mo\\prm20130801_01011000083.unl";
		
//		String mtFileName = "E:\\work\\hubei\\sms\\mt\\prm20130801_01012000120.unl";
		
		String strTemp;
		String[] eachValue;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(moFileName)));
			while(null != (strTemp = reader.readLine())) {
//				System.out.println(strTemp);
				eachValue = pattern.split(strTemp);
				if (StringUtils.isBlank(eachValue[2])) {
					System.out.println(strTemp);
				}
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
}
