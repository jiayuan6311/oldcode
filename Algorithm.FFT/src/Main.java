import java.io.*;
import java.math.BigDecimal;
import java.util.Vector;




public class Main {
	
	
	public static void main(String[] args)  {
		Poly p1,p2,p3;
		int maxi;
		W w,w1;
		Complex[] r1,r2,rr,r3,coeflist1,coeflist2;
		FFT fft1=new FFT();
		FFT fft2=new FFT();
		FFT fft3=new FFT();
		
		try {	
			
			File file=new File("doc/input4_3.dat");
			BufferedReader br=new BufferedReader(new FileReader(file));
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("doc/result4_3.dat")));
			maxi=Integer.parseInt(br.readLine())+1;
			String output = "";
			
			coeflist1=new Complex[maxi*2];     //递归用的参数
			coeflist2=new Complex[maxi*2];
			w=new W(Math.PI/maxi);
			double d=-Math.PI/maxi;
			w1=new W(d);
			r1=new Complex[maxi*2];
			r2=new Complex[maxi*2];
			rr=new Complex[maxi*2];
			r3=new Complex[maxi*2];
			String[] cc1=br.readLine().split(" ");         //读入p1
			for(int i=0;i<maxi*2;i++){
				if(i<maxi)coeflist1[i]=new Complex(Integer.parseInt(cc1[maxi-1-i]));
				else coeflist1[i]=new Complex(0);
				
			}
			p1=new Poly(maxi*2,coeflist1);                  
			
			String[] cc2=br.readLine().split(" ");        //读入p2
			for(int i=0;i<maxi*2;i++){
				if(i<maxi)coeflist2[i]=new Complex(Integer.parseInt(cc2[maxi-1-i]));
				else coeflist2[i]=new Complex(0);
			//	coeflist2[i].print();
			}
			p2=new Poly(maxi*2,coeflist2);
			
			
			fft1.FFT_op(p1, w, r1);                      //正向FFT算出两个多项式在各点的值
			fft2.FFT_op(p2, w, r2);
			
			for(int i=0;i<maxi*2;i++){
				rr[i]=r1[i].mul(r2[i]);
			//	r1[i].print();
			}
			p3=new Poly(maxi*2,rr);
			fft3.FFT_op(p3, w1, r3);
			for(int i=r3.length-1;i>=0;i--){
				r3[i].set(r3[i].realPart/(maxi*2), r3[i].imagenaryPart/(maxi*2));
			//	r3[i].print();
				boolean get=false;             //判断是否到达最高非零位
				
				
				BigDecimal bdr=new BigDecimal(r3[i].realPart).setScale(0, BigDecimal.ROUND_HALF_UP);
				BigDecimal bdi=new BigDecimal(r3[i].imagenaryPart).setScale(0, BigDecimal.ROUND_HALF_UP);
				if((!bdr.equals(new BigDecimal(0)))|(!bdi.equals(new BigDecimal(0)))){
					get=true;
				}
				if(get){
					if(!bdr.equals(new BigDecimal(0))){
						output=output+bdr.toString();
						if(bdi.equals(new BigDecimal(0)))output=output+",";
							else{output=output+"+"+bdi.toString()+"i,";}
					}
					if(bdr.equals(new BigDecimal(0))){
						if(!bdi.equals(new BigDecimal(0))){
							output=output+"+"+bdi.toString()+"i,";
						}
					}
					
				}
			}
			output=output.substring(0,output.length()-1);
			
			bw.write(output);
			bw.close();
			
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		

	}

}
