import java.util.Comparator;

class PointCompare implements Comparator
{
	public final int compare(Object a,Object b)      //���򷽷�
	{
		if(((Point)a).x>((Point)b).x)              //a��b�Ҳ�
		{
			return 1;
		}
		else                                       //a,b���ʱ
		{
			if( ((Point)a).x ==((Point)b).x )
			{
				if( ((Point)a).type > ((Point)b).type)//��ֱ���ں�
				{
					if( ((Point)b).io==1)
					{
						return 1;                       //add�Ⱥ����
					}
					else
					{
						return -1;                      //remove�������
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