package com.test.interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

public class IntervalTest {

	public static void main(String[] args) {
//		genData();
//		genList();
		List<int[]> inputList = genList();
		int randomI = 10000;
		
		long l = System.currentTimeMillis();
		
		erfenFind(inputList, randomI);
//		xunhuanFind(inputList, randomI);
		long ll = System.currentTimeMillis();
		
		System.out.printf("耗时：%s毫秒\n", ll - l );
		inputList = null;
		System.gc();
	}
	
	public static void xunhuanFind(List<int[]> inputList, int randomI) {
		for (int[] is : inputList) {
			if (is[0] <= randomI && is[1] >= randomI) {
				System.out.printf("%s:%s\n", is[0], is[1]);
			}
		}
	}
	
	public static void erfenFind(List<int[]> inputList, int randomI) {
		
		int index = findIndex(inputList, randomI, 0, inputList.size() -1);
		
		if (index > -1) {
			System.out.printf("%s:%s\n", inputList.get(index)[0], inputList.get(index)[1]);
		}
	}

	public static int findIndex(List<int[]> inputList, int randomI, int startI, int endI) {
		if (startI > endI || randomI < inputList.get(startI)[0] ||
				randomI > inputList.get(endI)[1]) {
			return -1;
		}
		int index = (startI + endI )/ 2;
		int[] is = inputList.get(index);
		if (is[0]<= randomI && is[1] >= randomI) {
			return index;
		}else if(randomI < is[0]) {
			return findIndex(inputList, randomI, startI, index -1);
		}else {
			return findIndex(inputList, randomI, index + 1, endI);
		}
	}
	
	
	/**
	 * 对数据段进行排序
	 * @param list
	 * @return
	 */
	public static List<int[]> sortList(List<int[]> dataList) {
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int[] is : dataList) {
			treeSet.add(is[0]);
			map.put(is[0], is[1]);
		}
		
		List<int[]> resultList = new ArrayList<int[]>();
		int[] resultInts;
		for (Integer integer : treeSet) {
			resultInts = new int[2];
			resultInts[0] = integer;
			resultInts[1] = map.get(integer);
			resultList.add(resultInts);
		}
		
		return resultList;
	}

	/**
	 * 初始化测试数据
	 */
	public static List<int[]> initData() {
		int[] i1 = { 1, 3 };
		int[] i2 = { 5, 10 };
		int[] i3 = { 11, 30 };
		int[] i4 = { 33, 45 };
		int[] i5 = { 46, 71 };
		int[] i6 = { 73, 89 };
		int[] i7 = { 100, 103 };
		int[] i8 = { 105, 155 };
		int[] i9 = { 167, 342 };

		List<int[]> dataList = new ArrayList<int[]>();
		dataList.add(i1);
		dataList.add(i4);
		dataList.add(i2);
		dataList.add(i3);
		dataList.add(i6);
		dataList.add(i5);
		dataList.add(i7);
		dataList.add(i8);
		dataList.add(i9);
		
		return dataList;
	}
	
	/**
	 * 随机生成数字段
	 */
	public static String genData() {
		Random random = new Random();
		List<String> list = new ArrayList<String>();
		int nowInt = 0;
		for(int i = 0; i< 1000000; i++) {
			int a = nowInt + random.nextInt(8) + 1;
			list.add(nowInt + "," + a);
			nowInt = a + 1;
		}
//		System.out.println(StringUtils.join(list, ","));
		return StringUtils.join(list, ",");
	}
	
	public static List<int[]> genList() {
		String s = genData();
		String[]  strs = s.split(",");
		List<int[]> list = new ArrayList<int[]>();
		int[] is;
		for (int i=0, n=strs.length; i<n; i=i+2) {
			is = new int[2];
			is[0] = Integer.parseInt(strs[i]);
			is[1] = Integer.parseInt(strs[i+1]);
			list.add(is);
		}
		return list;
	}
}
