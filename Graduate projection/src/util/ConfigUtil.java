package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 * @author: Wenbo Zhang
 * for getting configuration regulations
 */
public class ConfigUtil {
	
	private static Properties prop=new Properties();
	
	static {
		try{
			prop.load(new FileInputStream("config//config.properties"));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static List<String> getListProperity(String key){
		List<String> list=new ArrayList<String>();
		String str=prop.getProperty(key);
		try {
			str=new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int index=str.indexOf(' ');
		while(index>=0){
			str=str.substring(0, index)+str.substring(index+1, str.length());
			index=str.indexOf(' ');
		}
		index=str.indexOf('£»');
		while(index>=0){
			str=str.substring(0, index)+";"+str.substring(index+1, str.length());
			index=str.indexOf('£»');
		}
		index=str.indexOf(';');
		while(index>=0){
			if(index==0){
				str=str.substring(1, str.length());
				index=str.indexOf(';');
				continue;
			}
			list.add(str.substring(0, index));
			str=str.substring(index+1, str.length());
			if(str.length()>0)
				index=str.indexOf(';');
			else index=-1;
		}
		if(str.length()>0)
			list.add(str);
		return list;
	}
//change its Charset
	public static String getStringProperty(String key){
		String str=prop.getProperty(key);
		try {
			str=new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static int getIntegerProperty(String key){
		return Integer.parseInt(prop.getProperty(key));
	}
	
	public static long getLongProperty(String key) {
		return Long.parseLong(prop.getProperty(key));
	}
	public static boolean getBooleanProperty(String key) {
		return Boolean.parseBoolean(prop.getProperty(key));
	}
}
