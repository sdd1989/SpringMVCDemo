package com.ql.qiqi;

public class Consumer {

	public static  void consumer(String name, byte prdtnum, goods[] Good) {

		int Goods = WareHouse.read_num(Good) ;
		int j= 0, i=0;
		System.out.println("consumer name: " + name);
		System.out.println("consumer good name: " + Good[i].good_name);
	
		if( prdtnum <= Goods ) {
			for( i=0; i<prdtnum; i++) 
				System.out.println(WareHouse.read_id(i, Good));
			
			i--;
			j=i+1;
			int t = j;
			while( Good[j].getid() != null ){
				Good[j-t].good_id = Good[j].good_id;
				Good[j].good_id = null;
				j++;
			}


		}
		else if( prdtnum > Goods ) {
			int num=0;
			num = prdtnum/5;
			for( i=0; i<num+1; i++)
				Producer.prodecer(1, Good);
			
			for( i=0; i<prdtnum; i++) 
				System.out.println(WareHouse.read_id(i, Good));
			
			i--;
			j=i+1;
			int t = j;
			while( Good[j].getid() != null ){
				Good[j-t].good_id = Good[j].good_id;
				Good[j].good_id = null;
					//System.out.print(j);
				j++;
			}
			
		}
		
	}
}
