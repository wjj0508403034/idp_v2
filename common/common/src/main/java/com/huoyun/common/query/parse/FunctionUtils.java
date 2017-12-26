package com.huoyun.common.query.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FunctionUtils {

	public static final String Today = "today";
	public static final String Yesterday = "yesterday";
	public static final String Last7Days = "last_7_days";
	public static final String Last30Days = "last_30_days";
	public static final String ThisMonth = "this_month";
	public static final String LastMonth = "last_month";

	private final static List<String> FunctionList = new ArrayList<>();

	static {
		FunctionList.add(Today);
		FunctionList.add(Yesterday);
		FunctionList.add(Last7Days);
		FunctionList.add(Last30Days);
		FunctionList.add(ThisMonth);
		FunctionList.add(LastMonth);
	}

	public static boolean isFunc(String value) {
		for (String func : FunctionList) {
			if (StringUtils.equalsIgnoreCase(func, value)) {
				return true;
			}
		}

		return false;
	}
}
