package com.ipv6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class IPv6 {

	private static final Pattern PATTERN = Pattern
			.compile("([0-9a-fA-F]+)");
	
	private static final Pattern PATTERN2 = Pattern
			.compile("^([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)([0-9a-fA-F]+:)$");

	public static void main(String[] args) {

		int count = 1000000;
		
		long l = System.currentTimeMillis();
		for(int i=0; i<count; i++) {
			getFullIpv6("201:0DB8::2de:3432");
			
//			System.out.println(getFullIpv6("201:0DB8::2de:3432"));
		}
		System.out.println(System.currentTimeMillis() - l);

		l = System.currentTimeMillis();
		for(int i=0; i<count; i++) {
			getFullIpv62("201:0DB8::2de:3432");
			
//			System.out.println(getFullIpv62("201:0DB8::2de:3432"));
		}
		System.out.println(System.currentTimeMillis() - l);
	}
	
	private static String getFullIpv62(String str) {
		String ipv6 = formatIpv6IP(str);
		Matcher matcher = PATTERN2.matcher(ipv6.concat(":"));
		StringBuffer sb = new StringBuffer(45);

		while (matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i < count; i++) {
				sb.append(leftPad(matcher.group(i+1), 5));
			}
		}
		return StringUtils.substringBeforeLast(sb.toString(), ":");
	}
	
	private static String getFullIpv6(String str) {
		String ipv6 = formatIpv6IP(str);
		Matcher matcher = PATTERN.matcher(ipv6);
		StringBuffer sb = new StringBuffer(45);

		while (matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i < count; i++) {
				matcher.appendReplacement(sb, leftPad(matcher.group(i+1), 4));
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	private static String formatIpv6IP(String ipV6Addr) {
		String strRet = ipV6Addr;
		StringBuffer replaceStr;
		int iCount = 0;
		char ch = ':';

		if (StringUtils.isNotEmpty(ipV6Addr) && ipV6Addr.indexOf("::") > -1) {
			for (int i = 0; i < ipV6Addr.length(); i++) {
				if (ch == ipV6Addr.charAt(i)) {
					iCount++;
				}
			}

			if (ipV6Addr.startsWith("::")) {
				replaceStr = new StringBuffer("0000:0000:");
				for (int i = iCount; i < 7; i++) {
					replaceStr.append("0000:");
				}
			} else if (ipV6Addr.endsWith("::")) {
				replaceStr = new StringBuffer(":0000:0000");
				for (int i = iCount; i < 7; i++) {
					replaceStr.append(":0000");
				}
			} else {
				replaceStr = new StringBuffer(":0000:");
				for (int i = iCount; i < 7; i++) {
					replaceStr.append("0000:");
				}
			}
			strRet = ipV6Addr.trim().replaceAll("::", replaceStr.toString());
		}

		return strRet;
	}

	private static String leftPad(String str, int size) {
		return StringUtils.leftPad(str, size, '0');
	}
}