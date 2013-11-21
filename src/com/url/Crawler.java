package com.url;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Crawler {
	
	public static void main(String[] args) throws Exception{
		String urlPath = "http://www.hao123.com";
		URL url = new URL(urlPath);
//		System.out.println(url.openConnection().getContent().toString());
		
		
		System.out.println(downloadPage(url));
	}

	public static String downloadPage(URL url) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					(InputStream)url.getContent()));
			
			String line;
			StringBuffer pageBuffer = new StringBuffer(1000);
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}
			
			return pageBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
