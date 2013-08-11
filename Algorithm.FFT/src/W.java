
public class W{
	
	double angle;
	Complex value;
	
	public W(double a) {
		
		angle=a;
		double temre=Math.cos(angle);
		double temim=Math.sin(angle);
		value=new Complex(temre,temim);
	}

	
}
