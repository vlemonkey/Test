package com.ikanalyze.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzeUtils {

	private static Analyzer analyzer = null;
	
	static {
		analyzer = new IKAnalyzer(true);
	}
	
	public static List<String> analyze(String str) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(str)) {
			TokenStream tokenStream = analyzer.tokenStream("", new StringReader(str));
			try {
				while (tokenStream.incrementToken()) {
					String word = String.valueOf(tokenStream.getAttribute(CharTermAttribute.class));
					if (StringUtils.isNotBlank(word) &&
							word.length() > 1) {
						list.add(word);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
