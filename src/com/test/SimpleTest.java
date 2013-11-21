package com.test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unused")
public class SimpleTest {
	public static void main(String[] args) throws Exception {
		String str = "676/2012040512";
		Pattern datePattern = Pattern.compile("(.*)(\\d{4})(\\d{2})(\\d{2})(\\d{2})");
		System.out.println(datePattern.matcher(str).replaceAll("$1$2-$3-$4_$5"));
		
	}
	
	public static void nameTest() {
		String source = "C:/BOCO/test/source/guiyang/TD-SCDMA_MRO_DATANG_OMCR_2154_20121125030000.xml.gz";
		String tempString = source.substring(0, source.lastIndexOf("/"));

		int tempEndInt = tempString.lastIndexOf("/");
		int tempStartInt = tempString.lastIndexOf("/", tempEndInt - 1);
		String omc_id = tempString.substring(tempStartInt + 1, tempEndInt);
		String dataTime = tempString.substring(tempEndInt + 1);
		System.out.printf("omcid:%s\tdatatime:%s\n", omc_id, dataTime);
		
		Pattern p = Pattern.compile(".*_(\\d{4})(\\d{2})(\\d{2})(\\d{2}).*");
		String s = p.matcher(source).replaceAll("$1-$2-$3_$4");
		System.out.println(s);
	}

	/**
	 * 将数组元素转大写
	 * @param strData
	 */
	public static void arrayToUpperCase(String[] strData) {
		int count = strData.length;
		while (count-- > 0) {
			strData[count] = strData[count].toUpperCase();
		}
	}
	public static void testArrayCopy() {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = new int[10];
		for (int i : b) {
			b[i] = 0;
		}
		System.arraycopy(a, 1, b, 0, 4);
		for (int i : b) {
			System.out.print(i);
		}

		// List<String> list = new ArrayList<String>();
		// list.add("1");
		// list.add("2");
		// list.add("3");
		// list.add("4");
		// list.add("5");
		// list.add("6");
		// list.add("7");
		// list.add("8");
		//
		// List<String> list2 = new ArrayList<String>();
		// System.arraycopy(list, 0, list2, 0, 4);
		// for (String string : list2) {
		// System.out.println(string);
		// }
	}

	public static void test2() {
		long time = System.currentTimeMillis();

		for (long i = 0; i < 2000000000; i++) {
			String s = false ? null : null;
		}

		System.out.println("'��Ԫ�����':��ʱ" + (System.currentTimeMillis() - time)
				/ 1000.0 + "��");
	}

	public static void test() {
		long time = System.currentTimeMillis();

		for (long i = 0; i < 2000000000; i++) {
			if (false) {
				String s = null;
			} else {
				String s = null;
			}
			;
		}

		System.out.println("'if else':��ʱ" + (System.currentTimeMillis() - time)
				/ 1000.0 + "��");
	}

	public static void testProccess() {
		String command = "oozie job -kill "
				+ "0000131-130518153410188-oozie-oozi-C@1" + " -oozie "
				+ "http://CLOUD238:11000/oozie";
		try {
			System.out.printf("execute: %s success\n", command);
			Runtime.getRuntime()
					.exec(new String[] { "/bin/sh", "-c", command });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testString() {
		String str = "20140403";
		System.out.println(StringUtils.rightPad(str, 12, "0"));
	}

	private static String[] pattern = new String[] {
			"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM",
			"yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss",
			"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };

	public static void testDateFormat() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
		String strDate = "Sat May 18 11:47:37 CST 2013";
		System.out.println(sdf.parse(strDate));
	}

	public static void testEncode() throws Exception {
		String str = "user=tucu";
		String str2 = "user%3Dbansalm";
		System.out.println(URLEncoder.encode(str, "UTF-8"));
		System.out.println(URLDecoder.decode(str2, "UTF-8"));
	}

	// ipת��16����
	private static Pattern p3 = Pattern.compile("\\.");

	public static void getHexIp() {
		String ip = "10.168.1.221";
		String[] ips = p3.split(ip);
		for (String str : ips) {
			System.out.println(Integer.toHexString(Integer.parseInt(str)));
		}
	}

	// ������ʽ ����ʱ����� 201303201600
	private static Pattern p2 = Pattern.compile("[^\\d]");

	public static void getDateKey() {
		String strDate = "2013-03-20 16:00:52.876217";
		String s = p2.matcher(strDate).replaceAll("");
		System.out.println(StringUtils.substring(s, 0, 12));
	}

	// split �����и� | ����
	public static void splitTest() {
		String str = "|||||| ";
		String[] strs = str.split("\\|");
		System.out.println(strs.length);
		for (String string : strs) {
			System.out.println(string);
		}
	}

	// ������ʽ ��ȡParam����
	public static void testParam() {
		String str = "http://csc.360buy.com/log.ashx?type1=J&type2=A&pin=-&uuid=688631539&sid=688631539|";
		System.out.println(str.replaceAll("[^:]*://[^/]*/", ""));
		System.out.println(str.concat("/"));
	}

	// ������ʽ ��ȡurl����
	private static Pattern p = Pattern.compile("([^\\|]*\\|){27}");

	public static void testURL() {
		String str = "13972298700|33|28803|29523|358938041601270|872|2013-03-20 15:58:53.9991520|"
				+ "2013-03-20 15:58:58.5832320|5|1467|694|2161|2|10.153.75.242|58.83.238.152|"
				+ "200|Mozilla/5.0 (Linux; U; Android 4.0.2; zh-cn; Galaxy Nexus Build/ICL53F) "
				+ "AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobi|CMNET|"
				+ "460006263830381|221.177.161.134|221.177.161.34||37900|80|0||csc.360buy.com|"
				+ "http://csc.360buy.com/log.ashx?type1=J&type2=A&pin=-&uuid=688631539&sid=688631539|"
				+ "1&referrer=-&jinfo=UA-J2011-1||0||32-bit||720x1280||zh-cn||GBK||"
				+ "||read.360buy.com||11.1%20r115||linux||other||0||1363766274||1363766274||1363766274||1||3||direct||-||none||-||0||-&data=1363766334527&callback=jsonp1363766296543&_=1363766334539";
		// System.out.println(str.replaceFirst("(?:[^\\|]*\\|){27}", ""));

		String str2 = "13507282827|30|28803|62119|353637058269110|872|2013-03-20 16:00:09.7867460|2013-03-20 16:00:11.5458720|2|349|911|1260|2|10.184.120.32|222.73.125.92|200|Android|CMNET|460007642929151|221.177.161.201|221.177.161.31|text/json|46666|80|0||t.emoney.cn|http://t.emoney.cn/androidTS/r.aspx?nid=139141";
		String str3 = str2.replaceFirst("([^\\|]*\\|){27}", "");
		System.out.println("str2 URL : " + str3);
		System.out.println("-------------");
		System.out.println(StringUtils.removeEnd(str2, str3));
		// System.out.println(str.replaceAll("[^\\|]", ""));
	}
}
