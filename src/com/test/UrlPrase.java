package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlPrase {
	public static void main(String[] args) throws Exception{
		String url = "%E6%9D%8E%E8%A1%8C%E4%BA%AE-%E6%84%BF%E5%BE%97%E4%B8%80%E4%BA%BA%E5%BF%83";
		
		ArrayList<String> list = getChineseWordFormUrl(url);
		for (String str : list) {
			System.out.println(str);
		}
	}
	
	// 过滤url中的中文
	private static Pattern patternurl_zh_code = Pattern.compile("(%([a-fA-F]|[0-9]){2})+") ;
	private static Pattern patternurl_zh = Pattern.compile("[\u4E00-\u9FA5]+") ;
	private static Pattern pattern_enter = Pattern.compile("[\\r\\n\\t\\s]");

	/**
	 * 截取url中得中文字存放到arrlist里面
	 * @param url
	 * @return
	 */
	 public static ArrayList<String> getChineseWordFormUrl(String url) 
			 throws UnsupportedEncodingException{
			
		 ArrayList<String> hotwords = new ArrayList<String>();
 		 Matcher zh_code = null;
 		 Matcher zh_word = null;
 		 
 		 zh_code = patternurl_zh_code.matcher(url);
 		 
 		 while(zh_code.find()){
 			 String tmp = URLDecoder.decode(zh_code.group(), "utf-8");
 			 zh_word = patternurl_zh.matcher(tmp);
 			 while(zh_word.find()){
 				 hotwords.add(zh_word.group());	 	 
 			 }
 		 }
 		return hotwords;
 	}
	 
	 /**
		 * 截取url中得中文字存放到arrlist里面
		 * @param url
		 * @return
		 */
		 public static List<String> getChineseWordFormParam(String url) throws UnsupportedEncodingException{
	 		 ArrayList<String> hotwords = new ArrayList<String>();
	 		 Matcher zh_code = null;
	 		 Matcher zh_word = null;
	 		 
	 		 zh_code = patternurl_zh_code.matcher(url);
	 		 
	 		 while(zh_code.find()){
	 			 String tmp = URLDecoder.decode(zh_code.group(), "utf-8");
	 			 zh_word = patternurl_zh.matcher(tmp);
	 			 while(zh_word.find()){
	 				 hotwords.add(pattern_enter.matcher(zh_word.group())
	 						 .replaceAll(""));	 	 
	 			 }
	 		 }
	 		return hotwords;
	 	}
}
