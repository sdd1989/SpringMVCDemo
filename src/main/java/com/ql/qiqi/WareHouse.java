package com.ql.qiqi;

public class WareHouse {

	public static  void save(String good_id, int id, goods[] Good) {

		
		Good[id].setid(good_id);

	}
	public static  String read_id(int i, goods[] Good) {

		return Good[i].getid();
		
	}
	public static  int read_num(goods[] Good) {

		int j=0;

		while( Good[j].getid() != null ){	
			j++;
			//System.out.println(Good[j].getid());
		}
		
		//System.out.println(j);

		return j;
	}
}
