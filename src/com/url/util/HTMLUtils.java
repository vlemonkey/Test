package com.url.util;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLUtils {

	private static final String EMPTY = "";
	private static final Pattern allBlankPattern = Pattern.compile("[^\\u4E00-\\u9FFF]");

	public static void main(String[] args) { 
		System.out.println(getContent("http://www.hao123.com/hardware"));
	}

	public static String getHeader(Document doc) {
		StringBuffer sb = new StringBuffer(200);
		sb.append(doc.title());
		Elements eles = doc.getElementsByTag("meta");
		String temp;
		for (Element ele : eles) {
			temp = ele.toString();
			if (StringUtils.containsIgnoreCase(temp, "keywords")
					|| StringUtils.containsIgnoreCase(temp, "description")) {
				sb.append(ele.attr("content"));
			}
		}

		return allBlankPattern.matcher(sb.toString()).replaceAll(
				StringUtils.EMPTY);
	}

	public static String getHeader(String url) {
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			return getHeader(doc);
		} catch (Exception e) {
			System.out.printf("error url:%s\n", url);
		}

		return StringUtils.EMPTY;
	}

	public static String getContent(Document doc) {
		return getClearString(doc.text());
	}
	
	public static String getContent(String url) {
		String ret = EMPTY;
		try {
			Document doc = Jsoup.connect(url).timeout(3000).get();
			ret = getClearString(doc.text());
			doc = null;
		} catch (IOException e) {
			System.out.println("error url:\n" + url);
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * 去除不必要的东东
	 * 
	 * @param str
	 * @return
	 */
	private static String getClearString(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = allBlankPattern.matcher(str).replaceAll(StringUtils.EMPTY);
		}
		return str;
	}
}
