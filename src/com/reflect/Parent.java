package com.reflect;

import java.util.Map;

public abstract class Parent {
	abstract String run(String[] sts, Map<String, Map<String, String>> map, String paramters);
	abstract boolean filter(String[]  strs, Map<String, Map<String, String>> map, String paramters);
}
