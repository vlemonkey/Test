import static org.apache.commons.lang.StringUtils.leftPad;

import java.util.Random;


public class MakeDataGn {
	// #1 手机号 #2 IMSI #3 时间 
	private static final String STR_DATA = "#1,,28964,65452,8698130101682700,1204,#2,#3,1543,125,183,308,2,10.219.34.211,211.137.58.20,,1,2954250A2B3F1B89E9106AFF9088FEBF,,p8.qhimg.com,http://p8.qhimg.com/t013bacc5e00e46a894.jpg";
	
	private static final String PRE_TIME = "201308";
	
	private static StringBuffer sb;
	
	private static String[] mobiles = {"137", "138", "139", "150", "151", "158", "159"};
	
	private static Random random = new Random();
	
	public static String getRandomData(String strDay) {
		String randomTime = getFixedTime(strDay);
		return STR_DATA.replace("#1", getRandomMobile()).replace("#2", randomTime)
				.replace("#3", randomTime);
	}
	
	/**
	 * 随机手机号
	 * @return
	 */
	private static String getRandomMobile() {
		return Des.encode(getPreMobile().concat(getAfterMobile()));
	}
	
	/**
	 * 随机手机号前3位
	 * @return
	 */
	private static String getPreMobile() {
		return mobiles[random.nextInt(7)];
	}
	
	/**
	 * 随机手机号后8位
	 * @return
	 */
	private static String getAfterMobile() {
		return leftPad(String.valueOf(random.nextInt(99999999)), 8, "0");
	}

	/**
	 * 获取
	 * @return
	 */
	private static String getFixedTime(String strDay) {
		sb = new StringBuffer();
		sb.append(strDay).append(getHour())
			.append(getMinutes()).append(getSecond());
		return sb.toString();
	}
	
	
	/**
	 * 获取
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getRandomTime() {
		sb = new StringBuffer();
		sb.append(PRE_TIME).append(getDay()).append(getHour())
			.append(getMinutes()).append(getSecond());
		return sb.toString();
	}
	
	
	private static String getDay() {
		return leftPad2String((random.nextInt(28) + 1), 2);
//		return "12";
	}
	
	private static String getHour() {
		return leftPad2String(random.nextInt(24), 2);
	}
	
	private static String getMinutes() {
		return leftPad2String(random.nextInt(60), 2);
	}
	
	private static String getSecond() {
		return leftPad2String(random.nextInt(60), 2);
	}
	
	private static String leftPad2String(int i, int count) {
		return leftPad(String.valueOf(i), count, "0");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
