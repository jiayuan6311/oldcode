package parse;

public class Main {

	public static void main(String argv[]) {
		String filename="testcases/queens.tig";
		
	
			try {
				new Parse(filename);
			} catch (Error e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	

}
