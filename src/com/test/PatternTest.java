package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternTest {
	public static void main(String[] args) {
		test3();
	}
	
	public static void test3() {
		String str = "<td align=\"right\">15,794,988</td> \r\n" + 
				"</tr>";
		Pattern pattern = Pattern.compile("(?:\\d+,)*\\d+(?=</td> \r\n</tr>)");
//		System.out.println(pattern.matcher(str).replaceAll("@"));
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
	public static void test2() {
		String str = "<b>Started at:</b> Thu May 16 17:53:30 CST 2013<br>";
		Pattern pattern = Pattern.compile("(?<=</b> ).*?(?=<br>)");
//		System.out.println(pattern.matcher(str).replaceAll("@"));
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			System.out.println(matcher.group(0));
		}
	}
	
	public static void test() {
//		Pattern pattern = Pattern.compile("");
		String str = "<b>Finished at:</b> Wed May 15 20:41:53 CST 2013<br>";
		System.out.println(str.matches("^<b"));
	}
}
