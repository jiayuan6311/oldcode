class Point
{
	public int x;
	public int y;
	public int y2;
	public int type;
	public int lineindex;
	public int io=0;
	Point(int a,int b,int c,int d,int number,int io)    //竖直线上点的构造
	{	
		x=a;
		y=b;
		y2=d;
		type=1;
		lineindex=number;
	}
	Point(int a, int b,int number,int iotype)           //水平线上点的构造
	{
		x=a;
		y=b;
		type=0;
		lineindex=number;
		io=iotype;
	}
	
	
	
}