package Test;

public class MyThread implements Runnable {

	int count = 1, number;
	
	public MyThread(int num){
		number = num;
		System.out.println("Creat thread" + number);
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("Tread"+ number + ":count" + count);
			if(++count==6) return;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<5; i++){
			new Thread(new MyThread(i+1)).start();
		}
	}

}
