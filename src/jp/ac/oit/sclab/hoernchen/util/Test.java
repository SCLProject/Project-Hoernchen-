package jp.ac.oit.sclab.hoernchen.util;

import java.util.Calendar;
import jp.ac.oit.sclab.hoernchen.util.LissUtil.*;


public class Test {
	public static void main(String[] args){
	
		Calendar o = Calendar.getInstance();
		Calendar n = Calendar.getInstance();
		
		o.add(Calendar.SECOND, 0);
		
		
		System.out.println("Old Calendar : "+Tools.dateToString(o.getTime(), Tools.FORMAT_TYPE_FULL));
		System.out.println("New Calendar : "+Tools.dateToString(n.getTime(), Tools.FORMAT_TYPE_FULL));
		
		DifferenceTime dT = new DifferenceTime(o, n);
		
		
		
		
		
		
		System.out.println("Difference : " +dT.getDifferenceTime() );
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
