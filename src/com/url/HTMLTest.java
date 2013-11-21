package com.url;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HTMLTest {

	private static final String ENCODE = "UTF-8";
	private static final String EMPTY = "";

	public static void main(String[] args) {
		String  url = "http://www.sac.net.cn/";
		getHrefs(url);
	}
	
	/**
	 * 读取url页面的所有连接地址
	 * 地址必须以http开头
	 * @param url
	 */
	public static void getHrefs(String url) {
		String link = null;
		List<String> hrefList = new ArrayList<String>();
		Parser parser;
		try {
			parser = new Parser(url);
			parser.setEncoding(ENCODE);

			NodeFilter filter = new TagNameFilter("a");

			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			
			for (int i = 0, n = nodeList.size(); i < n; i++) {
				Node node = nodeList.elementAt(i);
				link = node.getText().replaceAll(".*?\"(http:[^\"]*)\".*", "$1");
				if (StringUtils.startsWith(link, "http")) {
					hrefList.add(link);
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取页面内容
	 */
	public static void getHTMLContent() {
		try {
			Parser parser = new Parser("http://news.hao123.com/");
			parser.setEncoding(ENCODE);

			NodeFilter filter = new TagNameFilter("body");

			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			for (int i = 0, n = nodeList.size(); i < n; i++) {
				Node node = nodeList.elementAt(i);
				String str = node.toHtml();
				System.out.println(str.replaceAll("\\s", EMPTY)
						.replaceAll("<script[^>]*?>.*?</script>",EMPTY)
						.replaceAll("<[^>]*>", EMPTY));
				System.out.println("-------------------------");
//				if (StringUtils.isNotBlank(str)) {
//					System.out.println(str);
//					System.out.println("----------");
//				}
			}

//			TextExtractingVisitor visitor = new TextExtractingVisitor();
//			parser.visitAllNodesWith(visitor);
//			String textInPage = visitor.getExtractedText();
//			System.out.println(textInPage);

			/*for (NodeIterator i = parser.elements(); i.hasMoreNodes();) {
				Node node = i.nextNode();
				// System.out.println("getText:"+node.getText());
				// System.out.println("getPlainText:"+node.toPlainTextString().replaceAll("\\s|\\&nbsp;",
				// ""));
				// System.out.println("toHtmltoHtml:"+node.toHtml().replaceAll("\\&[^;]*;|\\s",
				// "")
				// .replaceAll("<script[^>]*?>.*?</script>", ""));
				System.out.println(node.toHtml().replaceAll("\\s", EMPTY));
				System.out
						.println("toHtml()     :"
								+ node.toHtml(true)
										.replaceAll("\\s", EMPTY)
										.replaceAll(
												"<script[^>]*?>.*?</script>",
												EMPTY)
										.replaceAll("<[^>]*>", EMPTY));
				System.out.println("toHtml(true) :"
						+ node.toHtml(true).replaceAll("<[^>]*>", EMPTY)
								.replaceAll("\\s", EMPTY));
				System.out.println("toHtml(false):"
						+ node.toHtml(false).replaceAll("<[^>]*>", EMPTY)
								.replaceAll("\\s", EMPTY));
				// System.out.println("toString:"+node.toString());
				System.out
						.println("=================================================");
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
