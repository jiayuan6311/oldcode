package util;

import java.text.DateFormat;
import java.util.Date;

public class Time {
	public static void main(String[] args){
		System.out.println(Time.getStringCurrentDate());
	}
	public static String getStringCurrentTime(){
		Date now=new Date();
		DateFormat df=DateFormat.getDateTimeInstance(2, 2);
		return df.format(now);
	}
	
	public static long getLongCurrentTime(){
		Date now=new Date();
		return now.getTime();
	}
	
	
	public static String getStringCurrentDate(){
		Date now=new Date();
		DateFormat df=DateFormat.getDateTimeInstance(2, 2);
		return df.format(now);
	}
	
	public static String getCurrentDate(){
		Date now=new Date();
		DateFormat df=DateFormat.getDateInstance(2);
		return df.format(now);
	}
}
