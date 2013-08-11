import java.util.Comparator;

class PointCompare implements Comparator
{
	public final int compare(Object a,Object b)      //排序方法
	{
		if(((Point)a).x>((Point)b).x)              //a在b右侧
		{
			return 1;
		}
		else                                       //a,b相等时
		{
			if( ((Point)a).x ==((Point)b).x )
			{
				if( ((Point)a).type > ((Point)b).type)//竖直线于后方
				{
					if( ((Point)b).io==1)
					{
						return 1;                       //add先横后竖
					}
					else
					{
						return -1;                      //remove先竖后横
					}
				}
				else
				{
					return -1;
				}
			}
			else
				return -1;
		}
	}
}