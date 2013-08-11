import java.util.Random;
import java.util.Scanner;

public class map_set


{     public int M,N;
      public int num[][];
      
      
      map_set()
      {
    	  Scanner input = new Scanner( System.in );
  	      System.out.println("请输入地图高度N");
  	      N=input.nextInt();
  	      System.out.println("请输入地图宽度M");
  	      M=input.nextInt();
      }
	
	
	public void creat_map()
	{
		Random r = new Random();
	    num = new int [N+1] [M+1];
	    for(int n=2;n<N;n++){
	    	for(int m=2;m<M;m++){
	    		num[n][m]=r.nextInt(10);
	        }
	    }
	    for (int p=1;p<=M;p++){
	  	    num[1][p]=-1;
		    num[N][p]=-1;
	    }
		  
	    for(int q=1;q<=N;q++){
	    	num[q][1]=-1;
	        num[q][M]=-1;
	    }
	    printN(num,N+1,M+1);
	    }                          //map初始化完成


public static void printN(int [] [] a,int N,int M){
	
 for(int n=0;n<N;n++){
  
  for(int m=0;m<M;m++){
  	if(n==0||m==0)
  		System.out.print(" ");
  	else{
  	if (a[n][m]==-1)
  		System.out.print("* ");
  	else
        System.out.print(a[n][m]+" ");
  	} 
  }
  System.out.println();
 }
 
}


	}                      //地图打印展示