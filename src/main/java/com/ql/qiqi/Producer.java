package com.ql.qiqi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Producer {

	 int total = 0;
	
	public static  void prodecer(int choice, goods[] Good) {
		Date t = new Date();
		SimpleDateFormat format0 = new SimpleDateFormat("yyyyMMdd");
		String T = format0.format(t.getTime());
		
		String good_id;
		int flag = 0;
		int Goods = WareHouse.read_num(Good);
		
		
		//System.out.print(Goods);

		if(Goods == 0 ) {
			good_id = "0000";
		}
		else {
			good_id = Good[Goods-1].getid();
			good_id = good_id.substring(8);

		}
		
		int id = 0;
		if(choice == 1) {
			
			for(int i=0; i<5; i++) {
				//Good[i] = new goods();
				if(Goods != 0) {
					good_id = Good[Goods-1].getid();
					good_id = good_id.substring(8);
				}
				id = Integer.parseInt(good_id) + 1;
				
				good_id = String.format(T+"%04d",id);

				WareHouse.save(good_id, id-1, Good);
				
				Goods++;
			}
		}
		else {

			for(int i=0; i<10; i++) {
				//Good[i] = new goods();
				if(Goods != 0) {
					good_id = Good[Goods-1].getid();
					good_id = good_id.substring(8);
				}
				id = Integer.parseInt(good_id) + 1;
				
				good_id = String.format(T + "%05d", id);

				WareHouse.save(good_id, id-1,Good);

				Goods++;
			}
		}
			
	}


}
