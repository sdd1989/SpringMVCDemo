package com.ql.qiqi;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserInterface {
	
	public static byte Mode = 1;
	public static String goods_num=null;
	
	public static boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	}
	
	
	static Scanner input = new Scanner(System.in);
	
	public static byte choicePrdtMode() {
		System.out.println("hello productor please choice Mode of production: 1:Nomal 2:Enhanced");

		Scanner input = new Scanner(System.in);
		String mode;
		mode = input.next();
		while( mode.equals("1") == false && mode.equals("2") == false ) {
			System.out.print("Format error please repeat input!");
			mode = input.next();
		}
		Mode = (byte) Integer.parseInt(mode);
		return Mode;
	}

	public static String InputConsumerName() {
		System.out.println("hello Consumer,input your name : ");

		String name = input.next();
		//Consumer.consumer(name, goods_num);
		return name;
	}

	public static byte InputNumOfPrdt() {
		System.out.println("hello Consumer,how many goods your want : ");

		//String goods_num;
		int flag=0;
		goods_num = input.next();
		while(true) {
		if( isNumeric(goods_num) == false ) {
			System.out.print("Format error please repeat input!");
			goods_num = input.next();
			
			continue;
		}
		if(Integer.parseInt(goods_num) > 255){
			System.out.print("Out num please repeat input!");
			goods_num = input.next();
			continue;
		}
		else break;
		}
		byte Goods_num = (byte)Integer.parseInt(goods_num);
		return Goods_num;
		
	}
	public static boolean choiceGameOver() {
		System.out.println("Game Over? 1:yes 2:no");

		String choice;
		choice = input.next();
		while( choice.equals("1") == false && choice.equals("2") == false ) {
			System.out.print("Format error please repeat input!");
			choice = input.next();
		}
		int Choice =  Integer.parseInt(choice);
		if(Choice == 1)
			return true;
		else return false;

	}
}
