
public class Figure {
	int left,right;
	int[] height;
	

	public Figure(int l,int r,int[] h){
		left=l;
		right=r;
		height=h;
	}
	
	
	public  int Getl(Figure f){
		int l=right-left;
		return l;
	}
	
}