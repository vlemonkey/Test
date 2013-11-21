package com.url.seo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.url.util.HTMLUtils;

public class Test {

	public static void readHead() {
		String url = "http://www.2177s.com";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.title();
			System.out.printf("title:%s\n", title);
			
//			Elements eles = doc.select("meta[name~=(?i)keywords|(?i)description]");
			
			Elements eles = doc.select("meta");
			System.out.println(eles.size());
			for (Element ele : eles) {
				if (StringUtils.containsIgnoreCase(url, title));
				if (ele.toString().matches(".*(?i)keywords.*")) {
					System.out.println(ele.attr("content"));
				}
//				System.out.println(ele.attr("content"));
			}

//			Elements eles = doc.getElementsByTag("meta");
//			for (Element ele : eles) {
//				System.out.printf("keys:%s\n", ele.attr("keywords"));
//				System.out.printf("desc:%s\n", ele.attr("description"));
//				System.out.println("----------------");
//			}
			doc = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void parseFile() {
		File file = new File("D:\\hao123url\\page\\war3.htm");
		try {
			Document doc = Jsoup.parse(file, "UTF-8");
			System.out.print(HTMLUtils.getHeader(doc));
			System.out.println(HTMLUtils.getContent(doc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		parseFile();
	}
}
