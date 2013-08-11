import java.util.*;
import java.io.*;

public class Path {
    private static Stack<Position> shortPath = new Stack<Position>();
	private static Position[] offset = new Position[4];
	public static int money,cash_tmp,used_money;
	public static int step=0;
	
	
	private static void shortestPath(Stack<Position> path, Position beginPos,Position endPos,int maze[][],map_set a){
		
		if(beginPos.equals(endPos))
		{
			
			if(path.size() < shortPath.size() || shortPath.isEmpty())
			{				
				if(cash_tmp>=0)
				{
				shortPath = (Stack<Position>)path.clone();
				money=cash_tmp;
				}
				return;
			}	
	}
		for (int k = 0; k < 4; k++)
	     {	
			int nRow = beginPos.getRow() + offset[k].getRow();
			int nColumn = beginPos.getColumn() + offset[k].getColumn();
			if(isCanGo(maze[beginPos.getRow()][beginPos.getColumn()], nRow, nColumn, maze))
			{   
				
			    Position pos = new Position(nRow, nColumn);
			    maze[nRow][nColumn] = maze[beginPos.getRow()][beginPos.getColumn()] + 1; 
			    cash_tmp-=a.num[nRow][nColumn];
			    path.push(pos);
			    shortestPath(path,pos,endPos,maze,a);
			    path.pop();
			    maze[nRow][nColumn]=0;
			    cash_tmp+=a.num[nRow][nColumn];
			}
	     }
	}
	
	private static boolean isCanGo(int value, int row, int column,int maze[][])
	{
		if(maze[row][column] == -1)
		{
			
			return false;
		}
		else
		{
			if(maze[row][column]==0)
			return true;
			else
				return value+1<maze[row][column];
		}
	}
	
	private static void display(Position beginPos)
	{
		if(null == shortPath || shortPath.isEmpty())
		{
			System.out.println("没有可选路径。");
		}
		else
		{
			
			System.out.print(beginPos.toString());
			for(Position pos : shortPath)
			{
				System.out.print("->"+pos.toString());
				
				step++;
			}
			System.out.println();
			System.out.println("步数:"+step);
			System.out.println("剩余路费:"+money);
		}
		
	}
	
	

	public static void main(String[] args) 
	{   
		
		offset[0] = new Position(0,-1);
		offset[1] = new Position(0,1);
		offset[2] = new Position(-1,0);
		offset[3] = new Position(1,0);
		map_set a=new map_set();
		a.creat_map();
		int[][] maze=new int[a.N+1][a.M+1];
		for(int i=0;i<=a.N;i++)
		{
			for(int j=0;j<=a.M;j++)
			{
		        if(a.num[i][j]==-1)
		        	maze[i][j]=-1;
		        else
		        	maze[i][j]=0;
			}
		}
		System.out.println("请输入您拥有的路费。");
		Scanner stdin = new Scanner( System.in );
		money=stdin.nextInt();
		int beginr=0,beginc=0;
		Position beginPos;
		do
		{
			if(a.num[beginr][beginc]==-1)
			{
				System.out.println("不可以以这个点作为起始点。");
			}
		System.out.println("请输入起始点坐标，行数=");
		beginr=stdin.nextInt();
		System.out.println("列数=");
		beginc=stdin.nextInt();
		beginPos=new Position(beginr,beginc);
		}while(a.num[beginr][beginc]==-1);
		
		int endr=0,endc=0;
		Position endPos;
		do
		{
			if(a.num[endr][endc]==-1)
				System.out.println("不可以以这个点作为结束点。");
		
		System.out.println("请输入结束点坐标，行数=");
		 endr=stdin.nextInt();
		System.out.println("列数=");
		 endc=stdin.nextInt();
	     endPos=new Position(endr,endc);
		}while(a.num[endr][endc]==-1);
	    cash_tmp=money-a.num[beginr][beginc];   
		shortestPath(new Stack<Position> () , beginPos, endPos, maze,a);
		display(beginPos);
		
	
		}
}
