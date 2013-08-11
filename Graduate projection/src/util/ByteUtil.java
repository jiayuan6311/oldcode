package util;

import java.io.UnsupportedEncodingException;

public class ByteUtil {
	public static void main(String[] args){
		System.out.println(ByteUtil.string2ISN("º«ÐÅ", "GB2312"));
	}
	public static String string2ISN(String str, String charset){
		String isn=str;
		try {
			byte[] bytes=str.getBytes(charset);
			StringBuffer sb=new StringBuffer();
			for(byte b: bytes)
				sb.append(byteToString(b));
			isn=sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return isn;
	}
	
	
	
	public static String byteToString(byte b) {
	    byte high, low;
	    byte maskHigh = (byte) 0xf0;
	    byte maskLow = 0x0f;

	    high = (byte) ((b & maskHigh) >> 4);
	    low = (byte) (b & maskLow);

	    StringBuffer buf = new StringBuffer();
	    buf.append(findHex(high));
	    buf.append(findHex(low));

	    return buf.toString();
	  }

	  private static char findHex(byte b) {
	    int t = new Byte(b).intValue();
	    t = t < 0 ? t + 16 : t;

	    if ((0 <= t) && (t <= 9)) {
	      return (char) (t + '0');
	    }

	    return (char) (t - 10 + 'A');
	  }
}
