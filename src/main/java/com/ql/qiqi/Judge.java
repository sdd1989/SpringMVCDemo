package com.ql.qiqi;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Judge {

	public static int judge(String text) {

		int flag = 0;
		
		String pattern1 = "\\d+([\\-]\\d)*([+\\-*/]\\d+)+";
		boolean isMatch1 = Pattern.matches(pattern1, text);
		
		String pattern2 = "(\\d+)*([\\-]\\d)*(\\([+\\-*/]\\d+\\))*([+\\-*/]\\d+)*([+\\-*/]\\([\\-]\\d+\\))*([+\\-*/]\\d+)*([+\\-*/]\\([\\-]\\d+\\))*([+\\-*/]\\d+)*([+\\-*/]\\([\\-]\\d+\\))*([+\\-*/]\\d+)*";
		boolean isMatch2 = Pattern.matches(pattern2, text);
		
		if(isMatch2) {
			flag = 1;
			return flag;
		} else {
			flag = 2;
			return flag;
		}
	}

}
