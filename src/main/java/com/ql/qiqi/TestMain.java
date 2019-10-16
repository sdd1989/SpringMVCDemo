package com.ql.qiqi;

public class TestMain {
	public static goods[] Good = new goods[50];
	public static void main(String[] args) {
		boolean over = false;
		
		for(int i = 0 ;i < 50;i++) {
			Good[i] = new goods();
		}
		
		goods[] p = Good;
		while(!over) {
			byte b = (byte) (Math.random()*2);
			if(b==0) {
				byte choice = UserInterface.choicePrdtMode();
				Producer.prodecer(choice, p);
			}else {
				String name = UserInterface.InputConsumerName();
				byte prdtnum = UserInterface.InputNumOfPrdt();
				Consumer.consumer(name, prdtnum,Good);
			}
			over = UserInterface.choiceGameOver();
		}		
	}
}

