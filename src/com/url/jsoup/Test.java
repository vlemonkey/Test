package com.url.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.url.util.HTMLUtils;

public class Test {
	
	private static final int REPEAT_NUM = 6;
	
	public static void main(String[] args) {
		String url = "http://mail.aliyun.com/";
		getWords(url);
	}
	
	public static void getWords(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
			String header = HTMLUtils.getHeader(doc);
			String content = HTMLUtils.getContent(doc);
			header = repeatWord(header, REPEAT_NUM);
			System.out.println(header);
			System.out.println(content);
			
//			List<String> headerList = IKAnalyzeUtils.analyze(header);
//			for (String string : headerList) {
//				System.out.println(string);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将传入字符串str重复N次
	 * @param str
	 * @param n
	 * @return
	 */
	private static String repeatWord(String str, int n) {
		StringBuilder sb = new StringBuilder(str.length() * n + 2);
		for (int i = 0; i < n; i++) {
			sb.append(str);
		}
		
		return sb.toString();
	}
	

	public static void test() {
		
		String url = "http://www.hao123.com/star/wangzhi";
		
		try {
			Document doc = Jsoup.connect(url).timeout(3000).get();
//			System.out.println(doc.text());
			
			Elements divs = doc.select("body");
			for (Element div : divs) {
//				System.out.println(div.child(0).child(0).text());
				Elements links = div.getElementsByTag("a");
				for (Element element : links) {
					System.out.println(element.attr("href"));
				}
				System.out.println("------------------------");
			}
			
//			Elements links = doc.getElementsByTag("a");
//			for (Element link : links) {
//				System.out.println(link.attr("href"));
//				System.out.println("--------------------------");
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
