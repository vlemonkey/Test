package com.ikanalyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ikanalyze.utils.IKAnalyzeUtils;


public class Test {
	public static void main(String[] args) throws Exception{
		test();
		
//		test2();
	}
	
	public static void test2() {
		String filePath = "D:\\hao123url\\subclass\\购物,服饰鞋包.txt";
		File file = new File(filePath);
		BufferedReader reader = null;
		String temp = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while (null != (temp = reader.readLine())) {
				IKAnalyzeUtils.analyze(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test()  throws Exception{
		String str = "梦芭莎中国人们很no坚强，苹果果然好吃";
//		str += "java中内部类的分类及用法 - 食品饮料 - 道客巴巴类的,java内部类,java,内部类的用法,Java,使用方法,内部类的,内部类种类及,内部类,java的java中内部类的分类及用法";
		
		Analyzer analyzer = new IKAnalyzer(true);
		TokenStream tokenStream = analyzer.tokenStream("", new StringReader(str));   
   
        while (tokenStream.incrementToken()) {    
        	String string = tokenStream.getAttribute(CharTermAttribute.class).toString();
        	System.out.println(string);
        	
        }    
		
		
	}
	
	
}
