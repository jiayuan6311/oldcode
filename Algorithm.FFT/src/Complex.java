
public class Complex {

	 double realPart;
	 double imagenaryPart;

	public Complex(double re){
		realPart=re;
		imagenaryPart=0;
	}
	
	public Complex(double re,double img){
		realPart=re;
		imagenaryPart=img;
	}

	public void set(double re,double img){
		realPart=re;
		imagenaryPart=img;
	}

	public Complex add(Complex a){
		return new Complex(realPart+a.realPart,imagenaryPart+a.imagenaryPart);
	}
	public Complex sub(Complex b){
		return new Complex(realPart-b.realPart,imagenaryPart-b.imagenaryPart);
	}
	public Complex mul(Complex c){
		return new Complex(realPart*c.realPart-imagenaryPart*c.imagenaryPart,realPart*c.imagenaryPart+imagenaryPart*c.realPart);
	}
	public Complex power(int n){
		Complex cc=new Complex(realPart,imagenaryPart);
		Complex c1=new Complex(realPart,imagenaryPart);
		if(n==0){
			cc.set(1, 0);
		}
		else{
			for(int i=1;i<n;i++){
				cc=cc.mul(c1);
			}
		}
		return cc;
	}
	public void print(){
		if(realPart==0)System.out.println(imagenaryPart+"i");
		else if(imagenaryPart==0)System.out.println(realPart);
		else if(imagenaryPart<0){imagenaryPart=0-imagenaryPart; System.out.println(realPart+"-"+imagenaryPart+"i");}
		else if(imagenaryPart>0)System.out.println(realPart+"+"+imagenaryPart+"i");
	}
}
