package com.ql.qiqi;

import java.util.regex.Pattern;

public class Calculate {

	public static void calculate(String text) {

		int flag = Judge.judge(text);
		
		if(flag == 1) {
			
			Pattern p1 = Pattern.compile("[()]+");
			String[] text1 = p1.split(text);
			String str_text = String.join("", text1);
					
			Pattern p2 = Pattern.compile("");
			String[] text2 = p2.split(str_text);
			
			int i = 0;
			for (i=0;i<text2.length;i++) {
//				 if(text2[i] = "0")
				System.out.println(text2[i]);
			}
			
		}
		

	}

}