import java.util.*;
import java.io.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File infile=new File("doc/input1_3.dat");
			File outfile=new File("doc/result1_3.dat");
			BufferedReader br=new BufferedReader(new FileReader(infile));
			BufferedWriter bw=new BufferedWriter(new FileWriter(outfile));
			
			String s= br.readLine();          //读文件，处理第一行
			Figure[] figs=new Figure[Integer.parseInt(s)];
			//for(int i=0;i<Integer.parseInt(s);i++)figs[i]=new Figure();
	
			int fignum = 0;
			String temps=null;
			int[] h1=new int[80000];
			while ((temps=br.readLine()) != null){  
			
				String[] l=temps.split(" ");             //将一行数据分割
				int left=Integer.parseInt(l[0]);
				int right=Integer.parseInt(l[2]);
				
				for(int i=left;i<right;i++){
					h1[i]=Integer.parseInt(l[1]);
				}
				figs[fignum]=new Figure(left,right,h1);
				fignum++;  
			}  
			Figure rf=Combine(figs);                     //递归处理生成RusltFigure
			
			
			Vector<Integer> outa=new Vector<Integer>();   //生成输出Vec
			int lasth=0;
			for(int i=rf.left;i<=rf.right;i++){
				if (rf.height[i]==lasth){}
				else {
					outa.add(i);
					outa.add(rf.height[i]);
					lasth=rf.height[i];
				}	
			}
			for(int i=0;i<outa.size();i++){            //写文件
				String ss=""+outa.get(i);
				bw.write(ss);
				bw.newLine();
			}
			bw.close();
			br.close();
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			}catch(IOException e1){}	
		}
	
	public static Figure Combine(Figure[] fig_list){
		int l=fig_list.length/2;
		Figure[] fl1=new Figure[l];
		Figure[] fl2=new Figure[fig_list.length-l];
		
		if (fig_list.length==1) return fig_list[0];
		
		else{
			for(int i=0;i<fig_list.length;i++){           //分解成大小接近的两个数组
				if(i<l) {
					//fl1[i]=new Figure();                  //实例化，需要？
					fl1[i]=fig_list[i];
				}
				else  {
					//fl2[i-l]=new Figure();
					fl2[i-l]=fig_list[i];
				}
			}                                            
			
			
			if(fl1.length==1&fl2.length==1){
				int mostl,mostr;                         //两个简单建筑的合并
				int[] h=new int[500000];
				
				if(fl1[0].left<=fl2[0].left)mostl=fl1[0].left;   //左右边界
				else mostl=fl2[0].left;                     
				if(fl1[0].right>=fl2[0].right)mostr=fl1[0].right;
				else mostr=fl2[0].right;
				
				for(int i=mostl;i<=mostr;i++){                    //高度
					if(fl1[0].height[i]<=fl2[0].height[i]){
						h[i]=fl2[0].height[i];
					}else h[i]=fl1[0].height[i];	
				}
				return new Figure(mostl,mostr,h);
			}else if(fl1.length==1&fl2.length==2){
				Figure[] ff=new Figure[2];
				ff[0]=fl1[0];
				ff[1]=Combine(fl2);
				return Combine(ff);	
			}else{
				Figure[] ff=new Figure[2];
				ff[0]=Combine(fl1);
				ff[1]=Combine(fl2);
				return Combine(ff);
			}		
		}
	}

		
	
}


