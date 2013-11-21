package com.test.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class ReadFile {
	public static void main(String[] args) {
		readTextA_ByClassPath();
		readTextA_ByProjectRelativePath();
//		readTextB_ByProjectRelativePath();
	}

	/**
	 * ͨ���������·����ȡ�����ڣ��ļ���ע�ⲻ�ԡ�/����ͷ
	 */
	public static void readTextA_ByProjectRelativePath() {
		System.out
				.println("-----------------readTextA_ByProjectRelativePath---------------------");
		File f = new File("src/com/test/properties/conf/conf.properties");
		String a = file2String(f, "GBK");
		System.out.println(a);
	}
	
	/**
	 * ͨ��CLASSPATH��ȡ�����ļ���ע���ԡ�/����ͷ
	 */
	public static void readTextA_ByClassPath() {
		System.out
				.println("-----------------readTextA_ByClassPath---------------------");
		InputStream in = ReadFile.class
				.getResourceAsStream("/com/test/properties/conf/conf.properties");
		String a = stream2String(in, "GBK");
		System.out.println(a);
	}


	/**
	 * ͨ���������·����ȡ�����⣩�ļ���ע�ⲻ�ԡ�/����ͷ
	 */
	public static void readTextB_ByProjectRelativePath() {
		System.out
				.println("-----------------readTextB_ByProjectRelativePath---------------------");
		File f = new File("doc/b.txt");
		String b = file2String(f, "GBK");
		System.out.println(b);
	}

	/**
	 * �ļ�ת��Ϊ�ַ���
	 * 
	 * @param f
	 *            �ļ�
	 * @param charset
	 *            �ļ����ַ���
	 * @return �ļ�����
	 */
	public static String file2String(File f, String charset) {
		String result = null;
		try {
			result = stream2String(new FileInputStream(f), charset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �ļ�ת��Ϊ�ַ���
	 * 
	 * @param in
	 *            �ֽ���
	 * @param charset
	 *            �ļ����ַ���
	 * @return �ļ�����
	 */
	public static String stream2String(InputStream in, String charset) {
		StringBuffer sb = new StringBuffer();
		try {
			Reader r = new InputStreamReader(in, charset);
			int length = 0;
			for (char[] c = new char[1024]; (length = r.read(c)) != -1;) {
				sb.append(c, 0, length);
			}
			r.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
