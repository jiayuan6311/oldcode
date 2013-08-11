


public class FFT {


	
	
	public void FFT_op(Poly p,W wroot,Complex[] v){

		
		
		if(p.maxindex==1) v[0]=p.coef[0];
		else{

			Poly p1,p2;
			W ww;
			
			ww=new W(wroot.angle*2);
			
			Complex[] c1=new Complex[p.maxindex/2];
			Complex[] c2=new Complex[p.maxindex/2];
			Complex[] u=new Complex[p.maxindex/2];
			Complex[] w=new Complex[p.maxindex/2];
			
			for(int i=0;i<p.maxindex;i++){
				if(i%2==0) 
					c1[i/2]=p.coef[i];
				if(i%2==1)
					c2[i/2]=p.coef[i];
			}
			p1=new Poly(p.maxindex/2,c1);
			p2=new Poly(p.maxindex/2,c2);
			
	
			FFT_op(p1,ww,u);
			if(c1.length==2){
				int a=0;
			}	
			FFT_op(p2,ww,w);
			
			for(int i=0;i<p.maxindex/2;i++){
				Complex ccc=wroot.value;
				Complex ccc1=ccc.power(i);
				Complex ccc2=ccc1.mul(w[i]);
				v[i]=u[i].add(ccc2);
				v[i+p.maxindex/2]=u[i].sub(ccc2);
			}
		}
	}	
}
