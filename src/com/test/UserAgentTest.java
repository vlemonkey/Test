package com.test;

import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentTest {
	public static void main(String[] args) {
		testUserAgent();
	}

	public static void testUserAgent() {
		String strUserAgent = "Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; vivo V2 Build/GRJ90) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safa";
//		String strUserAgent = "GoogleAnalytics/1.4.2 (Linux; U; Android 4.0.4; zh-cn; T29 Build/IMM76D)";
//		String strUserAgent = "SkyNet/1.5.1-1700(android:4.0.4;package:com.imangi.templerun2;lang:zh_CN;app_version:1.1.2;channel:ZQ0S0N00000;device_brand:Coo";
		UserAgent ua = new UserAgent(strUserAgent);
		System.out.println(ua.getBrowser());
		System.out.println(ua.getOperatingSystem());
	}
}
