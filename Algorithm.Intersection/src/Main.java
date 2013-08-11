
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;




public class Main
{
	public static void main(String[] args) throws IOException
	{
	                                               //�ļ����룬��ʼ��
		ArrayList arr=new ArrayList();             //��̬����  
		int cross=0;                               //����ĸ���
		File inputfile=new File("doc/input5_2.dat");
		File outputfile=new File("doc/result5_2.dat");
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(inputfile));
			
			
			int linenum=Integer.parseInt(br.readLine());
			int index=0;
			
		
			for(int ii=0;ii<linenum;ii++)
			{
				String[] a=br.readLine().split(" ");
				int[] b=new int[8];
				for(int i=0;i<4;i++)
				{
					b[i]=new Integer(a[i]);
				}
					if(b[0]==b[2])                    //X���,��ֱ�߶�
					{
						if(b[1]>b[3])                 //������С���
						{
							int tem=b[3];
							b[3]=b[1];
							b[1]=tem;
						}
						arr.add(new Point(b[0],b[1],b[2],b[3],index,1));
						index++;
					}
					else                              //Y��ȣ�ˮƽ�߶�
					{	                             //��С���
						if(b[0]>b[2])
						{
							int tem=b[2];
							b[2]=b[0];
							b[0]=tem;
						}
						arr.add(new Point(b[0],b[1],index,1));
						index++;
						arr.add(new Point(b[2],b[1],index,-1));
						index++;
					}		
			}
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		int size=arr.size();
		Point[] Pointlist =(Point[])arr.toArray(new Point[size]);   //��̬����ת��
		
		Arrays.sort(Pointlist,new PointCompare());            //������
		

		ArrayList reg=new ArrayList();
		for(int i=0;i<size;i++)
		{
			if(Pointlist[i].type==1)                          //������ʼ
			{				
				if(reg.isEmpty()){}
				else
				{
					int csize=reg.size();
					for(int j=0;j<csize;j++)
					{
						int candi=((Point)reg.get(j)).y;
						if(Pointlist[i].y<=candi&&Pointlist[i].y2>=candi)
						{
							cross++;
						}
					}
				}
			}
			else if(Pointlist[i].type==0)
			{
				if(Pointlist[i].io==1)
				{	
					reg.add(Pointlist[i]);
				}
				else
				{
					int csize=reg.size();
					for(int j=0;j<csize;j++)
					{
						int candi=((Point)reg.get(j)).lineindex;
						if(Pointlist[i].lineindex==candi)
						{
							reg.remove(j);
						}
					}
				}	
			}
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(outputfile));
		bw.write(Integer.toString(cross));
		bw.close();
		System.out.println("ˮƽ������ֱ�ߵĽ�����ĿΪ��"+cross);
	}


}