package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtil {
	
	public static void main(String[] args){
		System.out.println(URLUtil.ChineseCharacters2ISN("http://www.dmw.gov.cn/otype.asp?owen1=地名要闻", "UTF-8"));
	}
	
	public static String cutPattern(String origin, String pattern) {
		int position = origin.indexOf(pattern);
		if (position < 0)
			return origin;
		// origin.setURL(origin.substring(0, position));
		return origin.substring(0, position);
	}

	public static String htm2html(String origin) {
		int position = origin.indexOf("htm");
		if (position < 0)
			return origin;
		// String url=origin.substring(0, position);
		// origin.setURL(url+"html");
		return origin.substring(0, position) + "html";
	}

	public static String ChineseCharacters2ISN(String url, String charset){
		String regEx="[\\u4e00-\\u9fa5]";
		Pattern pattern=Pattern.compile(regEx);
		Matcher matcher=pattern.matcher(url);
		while(matcher.find()){
			int pos=matcher.start();
			String piece=matcher.group(0);
			piece=ByteUtil.string2ISN(piece, charset);
			String isn="";
			for(int i=0; i<piece.length(); i+=2){
				isn+="%";
				isn+=piece.substring(i, i+2);
			}
			url=url.substring(0, pos)+isn+url.substring(pos+1, url.length());
			pattern=Pattern.compile(regEx);
			matcher=pattern.matcher(url);
		}
		return url;
	}
	
	public static String getHost(String url) {
		int start = url.indexOf("://");
		if (start < 0)
			return null;
		if (start + 3 >= url.length())
			return null;
		start += 3;
		 int tail=url.indexOf("/", start+1);
		if(tail<0)
			return url.substring(start, url.length());
		tail = start;
		while (tail < url.length() && url.charAt(tail) != '/')
			tail++;
		if (tail == url.length())
			return null;
		return url.substring(start, tail);
	}

	public static boolean isLegalURL(String url) {
		if (url == null) {
			return false;
		}
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
		// return null;
	}
}
