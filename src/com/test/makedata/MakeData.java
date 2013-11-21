package com.test.makedata;

import java.util.Random;

public class MakeData {

	private static final String STR_DATA = "#1";
	private static Random random = new Random();
	private static String[] data = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
			"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
			"y", "z" };

	public static String getRandomData(String strDay) {

		return STR_DATA.replace("#1", getRandom());

	}

	private static String getRandom() {
		return data[random.nextInt(37)].concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(" ").concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)])
				.concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]).concat(data[random.nextInt(37)]);
	}
}
