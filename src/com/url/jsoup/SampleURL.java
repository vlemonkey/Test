package com.url.jsoup;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.url.util.HTMLUtils;

public class SampleURL {
	
	private static final int REPEAT_NUM = 6;
	private static final String REJECT = "抱歉您访问的页面被限制访问";
	
	public static void main(String[] args) {
		String sample = "娱乐";
		String inPath = "D:\\hao123url\\IKAnalyzer-orig\\".concat(sample);
		String outPath = "D:\\hao123url\\IKAnalyzer-result\\".concat(sample);
		readOneFile(inPath, outPath);
		
		String indexPath = "D:\\hao123url\\IKAnalyzer-index";
		rename2Index(indexPath);
		
		String url = "http://www.hazq.com/";
		getWords(url);
	}
	
	public static void rename2Index(String inPath) {
		File file = new File(inPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int index = 0;
			for (File f : files) {
				f.renameTo(new File(inPath.concat("\\").concat(String.valueOf(index))));
				System.out.printf("%d=%s\n", index++, f.getName());
			}
		}
	}
	
	private static void readOneFile(String inPath, String outPath) {
		File inFile = new File(inPath);
		if (inFile.exists()) {
			String strLine = null;
			String ret = null;
			BufferedReader reader = null;
			FileWriter fw = null;
			
			try {
				reader = new BufferedReader(new FileReader(inFile));
				fw = new FileWriter(new File(outPath));
				while (null != (strLine = reader.readLine())) {
					if (StringUtils.startsWith(strLine, "http://")) {
						Document doc = null;
						try {
							doc = Jsoup.connect(strLine).timeout(5000).get();
						} catch (Exception e) {
							System.out.println(strLine);
							continue;
						}
						ret = HTMLUtils.getHeader(doc);
						if (isNotBlank(ret)) {
							ret = repeatWord(ret, REPEAT_NUM);
							fw.write(ret.concat("\n"));
//							System.out.println(ret);
						}
						ret = HTMLUtils.getContent(doc);
						if (isNotBlank(ret) && !StringUtils.startsWithIgnoreCase(ret, REJECT)) {
							fw.write(ret.concat("\n"));
//							System.out.println(ret);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (null != fw) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != reader) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("SUCCESS");
			}
		}
	}

	private static void getWords(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(5000).get();
			String header = HTMLUtils.getHeader(doc);
			String content = HTMLUtils.getContent(doc);
			header = repeatWord(header, REPEAT_NUM);
			System.out.println(header);
			System.out.println(content);
			
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
}
