import java.util.Random;

/*
 * Created on 2010-7-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class test {

	public static void main(String[] args) {
		Random rd1 = new Random();
		String a=String.valueOf(rd1.nextInt(10));
	     
		System.out.print(a);
	}
}