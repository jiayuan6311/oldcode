import java.io.*;
import java.util.*;


public class Graph {
	int nodenum,DFS_now;
	int[][] adjmat;
	Vertex[] verlist;
	Stack stack=new Stack();
	String[] writestring=new String[50];
	int c1=0;
	public Graph(String filename) throws IOException {
		
		File infile=new File(filename);	
		BufferedReader br= new BufferedReader(new FileReader(infile));
		nodenum=Integer.valueOf(br.readLine());
		DFS_now=nodenum;
		adjmat=new int[nodenum][nodenum];
		verlist=new Vertex[nodenum];
		String temps=null;
		
		while((temps=br.readLine())!=null){             //读文件
			int start,end;
			String[] s1=temps.split(",");
			s1[0]=s1[0].replace("(", "");
			s1[1]=s1[1].replace(")", "");	
			start=Integer.valueOf(s1[0]);
			end=Integer.valueOf(s1[1]);
			adjmat[end][start]=1;
			adjmat[start][end]=1;
		}
		br.close();

		
		for(int i=0;i<nodenum;i++)verlist[i]=new Vertex(i);
		
	}
	public void BC(Vertex v){
		
		v.dfsnum=DFS_now;
		DFS_now-=1;
		stack.push(v);
		v.high=v.dfsnum;            //初始值
		
		int[] list=adjmat[v.index];
		
		
		for(int i=0;i<list.length;i++){                 //for all edges from v
			if(list[i]==1) {
				Vertex w=verlist[i];
				Edge ed=new Edge(v,w);
			
				stack.push(ed);
				if(w.index!=v.parent){
					if(w.dfsnum==0){
						w.setparent(v.index);
						BC(w);
						
						
						if(w.high<=v.dfsnum){         //判断v是否关节点
							String[] output = new String[nodenum];
							Object tem=stack.pop();
							int c=0;
							boolean j=true;
				
							do
							{
								if(tem instanceof Vertex){             //处理一个Object
									if(((Vertex) tem).name.equals(v.name)){
										j=false;
									}       
									output[c]=((Vertex) tem).name;
									c++;
								}					
								if(j)tem=stack.pop();                  //pop出下一个
										
							}while(j);
							writestring[c1]="(";
							for(int i1=output.length-1;i1>=0;i1--){
								if(output[i1]!=null){
									writestring[c1]+=output[i1]+",";
									
								}
							}
							c1++;	
							stack.push(v);	
						}
						v.high=Math.max(v.high,w.high);
					}
					else {v.high=Math.max(v.high, w.dfsnum);}

				}
			}
		}
	}

}
