import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("unused")
public class NewJFrame extends javax.swing.JFrame {
	private Board ChessBoard1;
	private JButton jButton1;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	static int Maze[][];  //每一点的具体情况 
	static int Cango[][];//是否能走
	private JTextField jTextField1;
	static JLabel label[][];
	static Accessible type1[][];
	static Accessible type2[][];
	static Accessible type3[][];
	
	
	static int money;
	static boolean ifset1can=true;
	private JButton jButton7;
	static boolean ifset2can=true;
	static boolean ifset3can=true;
	static boolean ifsetendcan=true;
	private JButton jButton10;
	private JButton jButton9;
	private JButton jButton8;
	static boolean ifcanstart=true;

	static boolean ifset1=false;
	static boolean ifset2=false;
	static boolean ifset3=false;
	static boolean ifsetend=false;
	static boolean canstart=true;
	static int begini1=100;
	static int begini2=100;
	static int begini3=100;
	static int endi1=100;
	static int beginj1=100;
	static int beginj2=100;
	static int beginj3=100;
	static int endj1=100;
	
	static int  speed=100;
	
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;

	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	
	
	class TestThread1 extends Thread                //人物1的路径尝试方法
	{		
		
		
		private Component ff;
		Accessible[][] type1=new Accessible[21][21];
		public void run() 
		{
			boolean find=false;
			Pointer pp=new Pointer(begini1,beginj1,money);
			ArrayList<Pointer> li=new ArrayList<Pointer>();
			li.add(pp);		
			
			
			
			for(int i=0;i<21;i++)
		{
				
				for(int j=0;j<21;j++)
				{
					type1[i][j]=new Accessible();
					type1[i][j].up=true;
					type1[i][j].down=true;
					type1[i][j].left=true;
					type1[i][j].right=true;
				}
		}
						
			//以下为测试行走方向的逻辑
			if(!find)
			{	if(li.isEmpty()){JOptionPane.showMessageDialog(ff, "Jefferson Lost！");}
			else{
				while(!li.isEmpty())				
			{
				
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {				
				}
				Pointer p=new Pointer();
				p=li.get(li.size()-1);        //返回上一个位置
				if(p.x==endi1&&p.y==endj1){JOptionPane.showMessageDialog(ff, "Jefferson Finish！");break;}
				
				
				
				//测试向上走
				if(type1[p.x][p.y].up){
				if(Maze[p.x][p.y+1]==10){}           //若为*
				else{	if(Maze[p.x][p.y+1]==-11||Maze[p.x][p.y+1]==-12){}//如果是别人的专属格子
				else//不是的话
					{
					if(Maze[p.x][p.y+1]==-10)//是否是自己的专属格子
						{
						type1[p.x][p.y].up=false;
				        jButton7.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);   //确定走过去
					    Pointer p1=new Pointer((p.x-1),(p.y),p.cash);
					    li.add(p1);         //加入路径列表中
					    continue;	
				    }
				    else//不是自己专属格子的话
				    {
					    if(p.cash+Maze[p.x][p.y+1]>=0&&Cango[p.x][p.y+1]==1)    //确定是否能走，钱是否够
					    {
					     	type1[p.x][p.y].up=false;
					    	jButton7.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);//真的走下来
					    	Pointer p1=new Pointer((p.x-1),(p.y),(p.cash+Maze[p.x][p.y+1]));
						    li.add(p1);
					    	continue;
					    	}
					    }
				    }
				}
				}            //实验结束
			
			//测试向右走，判断逻辑与向上走类似
				if(type1[p.x][p.y].right){
			if(Maze[p.x+1][p.y+2]==10){}         
			else{	if(Maze[p.x+1][p.y+2]==-11||Maze[p.x+1][p.y+2]==-12){}
			else
			{
				if(Maze[p.x+1][p.y+2]==-10)
				{
					type1[p.x][p.y].right=false;
						jButton7.setBounds(31+22*(p.y+1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y+2]>=0&&Cango[p.x+1][p.y+2]==1)
					{
						type1[p.x][p.y].right=false;
						jButton7.setBounds(31+22*(p.y+1), 31+22*(p.x), 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),(p.cash+Maze[p.x+1][p.y+2]));
						li.add(p1);
						continue;
					}
				}
			}
			}
			}                        	//实验结束
		
		//测试向右走，判断逻辑与向上走类似
		if(type1[p.x][p.y].left){
			if(Maze[p.x+1][p.y]==10){}
			else{if(Maze[p.x+1][p.y]==-11||Maze[p.x+1][p.y]==-12){}
			else
			{
				if(Maze[p.x+1][p.y]==-10)
				{
					
					type1[p.x][p.y].left=false;
						jButton7.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y]>=0&&Cango[p.x+1][p.y]==1)
					{
						type1[p.x][p.y].left=false;
						jButton7.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),(p.cash+Maze[p.x+1][p.y]));
						li.add(p1);
						continue;
					}

				}
			}
			}
			}                    //实验结束
		
		//测试向右走，判断逻辑与向上走类似
		if(type1[p.x][p.y].down){
			if(Maze[p.x+2][p.y+1]==10){}
			else{if(Maze[p.x+2][p.y+1]==-11||Maze[p.x+2][p.y+1]==-12){}
			else
			{
				if(Maze[p.x+2][p.y+1]==-10)
				{
					type1[p.x][p.y].down=false;
					jButton7.setBounds(31+22*p.y, 31+22*(p.x+1), 20, 20);
					Pointer p1=new Pointer((p.x+1),(p.y),p.cash);
					li.add(p1);
					continue;
				}
				else
				{
					if(p.cash+Maze[p.x+2][p.y+1]>=0&&Cango[p.x+2][p.y+1]==1)
					{
						type1[p.x][p.y].down=false;
						jButton7.setBounds(31+22*(p.y), 31+22*(p.x+1), 20, 20);
						Pointer p1=new Pointer((p.x+1),(p.y),(p.cash+Maze[p.x+2][p.y+1]));
						li.add(p1);
						continue;
					}

				}
			}}}
			
			}
			}
			}
		}
	
	}
	
	class TestThread2 extends Thread           //逻辑基本类似于第一个线程。
	{		
		private Component ff;
		Accessible[][] type2=new Accessible[21][21];
		public void run() 
		{
			boolean find=false;
			Pointer pp=new Pointer(begini2,beginj2,money);
			ArrayList<Pointer> li=new ArrayList<Pointer>();
			li.add(pp);
			for(int i=0;i<21;i++)
			{
					
					for(int j=0;j<21;j++)
					{
						type2[i][j]=new Accessible();
						type2[i][j].up=true;
						type2[i][j].down=true;
						type2[i][j].left=true;
						type2[i][j].right=true;
					}
			}
			
			
		
			if(!find)
			{if(li.isEmpty()){JOptionPane.showMessageDialog(ff, "Bob Lost！");}
			else{
				while(!li.isEmpty())				
			{
				
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {				
				}
				Pointer p=new Pointer();
				p=li.get(li.size()-1);
				if(p.x==endi1&&p.y==endj1){JOptionPane.showMessageDialog(ff, "Bob Finish！");break;}
				
				
				
			
				if(type2[p.x][p.y].up){
				if(Maze[p.x][p.y+1]==10){}
			else{	if(Maze[p.x][p.y+1]==-10||Maze[p.x][p.y+1]==-12){}
			else
			{
				if(Maze[p.x][p.y+1]==-11)
				{
					
					type2[p.x][p.y].up=false;
						jButton8.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);
						Pointer p1=new Pointer((p.x-1),(p.y),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x][p.y+1]>=0&&Cango[p.x][p.y+1]==1)
					{
						type2[p.x][p.y].up=false;
						jButton8.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);
						Pointer p1=new Pointer((p.x-1),(p.y),(p.cash+Maze[p.x][p.y+1]));
						li.add(p1);
						continue;
					}
					
				}
			}}}
		
			
			
				if(type2[p.x][p.y].right){
			if(Maze[p.x+1][p.y+2]==10){}
			else{	if(Maze[p.x+1][p.y+2]==-10||Maze[p.x+1][p.y+2]==-12){}
			else
			{
				if(Maze[p.x+1][p.y+2]==-11)
				{
					type2[p.x][p.y].right=false;
						jButton8.setBounds(31+22*(p.y+1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y+2]>=0&&Cango[p.x+1][p.y+2]==1)
					{
						type2[p.x][p.y].right=false;
						jButton8.setBounds(31+22*(p.y+1), 31+22*(p.x), 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),(p.cash+Maze[p.x+1][p.y+2]));
						li.add(p1);
						continue;
					}
					
				}
			}}}
			
				if(type2[p.x][p.y].left){
			if(Maze[p.x+1][p.y]==10){}
			else{if(Maze[p.x+1][p.y]==-10||Maze[p.x+1][p.y]==-12){}
			else
			{
				if(Maze[p.x+1][p.y]==-11)
				{
					
					type2[p.x][p.y].left=false;
						jButton8.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y]>=0&&Cango[p.x+1][p.y]==1)
					{
						type2[p.x][p.y].left=false;
						jButton8.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),(p.cash+Maze[p.x+1][p.y]));
						li.add(p1);
						continue;
					}
				}
			}}}
			
				if(type2[p.x][p.y].down){
			if(Maze[p.x+2][p.y+1]==10){}
			else{if(Maze[p.x+2][p.y+1]==-10||Maze[p.x+2][p.y+1]==-12){}
			else
			{
				if(Maze[p.x+2][p.y+1]==-11)
				{
					
					type2[p.x][p.y].down=false;
						jButton8.setBounds(31+22*p.y, 31+22*(p.x+1), 20, 20);
						Pointer p1=new Pointer((p.x+1),(p.y),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+2][p.y+1]>=0&&Cango[p.x+2][p.y+1]==1)
					{
						type2[p.x][p.y].down=false;
						jButton8.setBounds(31+22*(p.y), 31+22*(p.x+1), 20, 20);
						Pointer p1=new Pointer((p.x+1),(p.y),(p.cash+Maze[p.x+2][p.y+1]));
						li.add(p1);
						continue;
					}
				}
			}}}
			
			}
			}
			}
		}
		
	}
	
	
	class TestThread3 extends Thread      //逻辑也基本类似
	{		
		private Component ff;
		Accessible[][] type3=new Accessible[21][21];
		public void run() 
		{
			boolean find=false;
			Pointer pp=new Pointer(begini3,beginj3,money);
			ArrayList<Pointer> li=new ArrayList<Pointer>();
			li.add(pp);
			for(int i=0;i<21;i++)
			{
					
					for(int j=0;j<21;j++)
					{
						type3[i][j]=new Accessible();
						type3[i][j].up=true;
						type3[i][j].down=true;
						type3[i][j].left=true;
						type3[i][j].right=true;
					}
			}	
			
			
		
			if(!find)
			{if(li.isEmpty()){JOptionPane.showMessageDialog(ff, "Alice Lost！");}
			else{
				while(!li.isEmpty())				
			{
				
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {				
				}
				Pointer p=new Pointer();
				p=li.get(li.size()-1);
				if(p.x==endi1&&p.y==endj1){JOptionPane.showMessageDialog(ff, "Alice Finish！");break;}
				
				
				
		
		if(type3[p.x][p.y].up){
			if(Maze[p.x][p.y+1]==10){}
			else{	if(Maze[p.x][p.y+1]==-10||Maze[p.x][p.y+1]==-11){}
			else
			{
				if(Maze[p.x][p.y+1]==-12)
				{
					
					type3[p.x][p.y].up=false;
						jButton9.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);
						Pointer p1=new Pointer((p.x-1),(p.y),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x][p.y+1]>=0&&Cango[p.x][p.y+1]==1)
					{
						type3[p.x][p.y].up=false;
						jButton9.setBounds(31+22*(p.y), 31+22*(p.x-1), 20, 20);
						Pointer p1=new Pointer((p.x-1),(p.y),(p.cash+Maze[p.x][p.y+1]));
						li.add(p1);
						continue;
					}
				}
			}}}
				
		if(type3[p.x][p.y].right){
			if(Maze[p.x+1][p.y+2]==10){}
			else{	if(Maze[p.x+1][p.y+2]==-10||Maze[p.x+1][p.y+2]==-11){}
			else
			{
				if(Maze[p.x+1][p.y+2]==-12)
				{
					type3[p.x][p.y].right=false;
						jButton9.setBounds(31+22*(p.y+1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y+2]>=0&&Cango[p.x+1][p.y+2]==1)
					{
						type3[p.x][p.y].right=false;
						jButton9.setBounds(31+22*(p.y+1), 31+22*(p.x), 20, 20);
						Pointer p1=new Pointer(p.x,(p.y+1),(p.cash+Maze[p.x+1][p.y+2]));
						li.add(p1);
						continue;
					}
				}
			}}}

				if(type3[p.x][p.y].left){
			if(Maze[p.x+1][p.y]==10){}
			else{if(Maze[p.x+1][p.y]==-10||Maze[p.x+1][p.y]==-11){}
			else
			{
				if(Maze[p.x+1][p.y]==-12)
				{
					
					type3[p.x][p.y].left=false;
						jButton9.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+1][p.y]>=0&&Cango[p.x+1][p.y]==1)
					{
						type3[p.x][p.y].left=false;
						jButton9.setBounds(31+22*(p.y-1), 31+22*p.x, 20, 20);
						Pointer p1=new Pointer(p.x,(p.y-1),(p.cash+Maze[p.x+1][p.y]));
						li.add(p1);
						continue;
					}
				}
			}}}
				if(type3[p.x][p.y].down){
			if(Maze[p.x+2][p.y+1]==10){}
			else{if(Maze[p.x+2][p.y+1]==-10||Maze[p.x+2][p.y+1]==-11){}
			else
			{
				if(Maze[p.x+2][p.y+1]==-12)
				{
					
					type3[p.x][p.y].down=false;;
						jButton9.setBounds(31+22*p.y, 31+22*(p.x+1), 20, 20);
						Pointer p1=new Pointer((p.x+1),(p.y),p.cash);
						li.add(p1);
						continue;
					
				}
				else
				{
					if(p.cash+Maze[p.x+2][p.y+1]>=0&&Cango[p.x+2][p.y+1]==1)
					{
						type3[p.x][p.y].down=false;
						jButton9.setBounds(31+22*(p.y), 31+22*(p.x+1), 20, 20);
						Pointer p1=new Pointer((p.x+1),(p.y),(p.cash+Maze[p.x+2][p.y+1]));
						li.add(p1);
						continue;
					}
				}
			}}}
			
			}
			}
			}
			
		}
		
	}
	public NewJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {                         //棋盘的初始化
		try {
this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setResizable(false);
			this.setTitle("Maze of 3p\r\n");

			{
				
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Generate");
				jButton1.setBounds(527, 33, 92, 72);

				jButton1.addMouseListener(new NewMouseListener(){
					private JLabel jLabel2;

					public void mousePressed(MouseEvent arg0) 
					{			
						if(ifcanstart)
						{Maze=new int[23][23];
						Cango=new int[23][23];
						JLabel[][] label=new JLabel[21][21];
						for(int i=0;i<23;i++)               //随机生成整数值，代表各方格情况
						{							
							for(int j=0;j<23;j++)
							{								
								Maze[i][j]=(int)((Math.random()*24)-13);		
								Cango[i][j]=1;
							}
							
						}				
						for(int i=0;i<23;i++)//赋值为10   即为星号
						{							
							for(int j=0;j<23;j++)
							{								
								if(i==0||i==22||j==0||j==22)
								Maze[i][j]=10;							
							}
							
						}
						
						for(int i=0;i<21;i++)    //给LABEL赋值
						{							
							for(int j=0;j<21;j++)
							{								
								label[i][j]=new JLabel();
								ChessBoard1.add(label[i][j]);
								String a=""+Maze[i+1][j+1];
								if(Maze[i+1][j+1]==10){a="*";}//不能走
								if(Maze[i+1][j+1]==0){a="";}
								if(Maze[i+1][j+1]==-10){a="J";}
								if(Maze[i+1][j+1]==-11){a="B";}
								if(Maze[i+1][j+1]==-12){a="A";}
								label[i][j].setText(a);
								label[i][j].setBounds(33+j*22, 36+i*22,22, 11);
												
							}
						}ifcanstart=false;
						}
						else{}
						jButton2.setVisible(true);
						
					}
					
				});
				
				
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Jefferson\u8d77\u70b9");
				jButton2.setBounds(527, 105, 92, 69);
				jButton2.setVisible(false);

				jButton2.addMouseListener(new NewMouseListener(){
					public void mousePressed(MouseEvent arg0) {
				
						if(ifset1can)
						{ifset1=true;}
						
					}
					
				});
				
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("Bob\r\n\u8d77\u70b9");
				jButton3.setBounds(527, 172, 92, 69);
				jButton3.setVisible(false);
				jButton3.addMouseListener(new NewMouseListener(){
					public void mousePressed(MouseEvent arg0) {
						if(ifset2can)
						{ifset2=true;}
						

					}
					
				});
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4);
				jButton4.setText("Alice\u8d77\u70b9");
				jButton4.setBounds(527, 241, 92, 66);
				jButton4.setVisible(false);

				jButton4.addMouseListener(new NewMouseListener(){
					public void mousePressed(MouseEvent arg0) {
						if(ifset3can)
						{ifset3=true;}
						

					}
					
				});
			}
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5);
				jButton5.setText("End");
				jButton5.setBounds(527, 307, 92, 66);
				jButton5.setVisible(false);

				jButton5.addMouseListener(new NewMouseListener(){
					public void mousePressed(MouseEvent arg0) {
						jTextField1.setVisible(true);
						if(ifsetendcan)
						{ifsetend=true;}
						

					}
					
				});
			}
			{
				jButton6 = new JButton();
				getContentPane().add(jButton6);
				jButton6.setText("Begin\uff01");
				jButton6.setBounds(527, 430, 92, 64);
				jButton6.setVisible(false);

				jButton6.addMouseListener(new NewMouseListener(){
				private Component ff;

				public void mousePressed(MouseEvent arg0) {
					if(canstart)
					{
						try{money=Integer.parseInt(jTextField1.getText());}
						catch(NumberFormatException e){JOptionPane.showMessageDialog(ff, "Wrong Number！Default cash is 1000.");money=1000;}
						new TestThread1().start();
						new TestThread2().start();
						new TestThread3().start();canstart=false;}
					}		
				});
			}
			{
				ChessBoard1=new Board(0,0);
				getContentPane().add(ChessBoard1);
				ChessBoard1.setBounds(0, -1, 515, 515);
				{
					jButton7 = new JButton();
					ChessBoard1.add(jButton7);
					jButton7.setBounds(100, 5, 20, 20);
					jButton7.setBackground(new java.awt.Color(255,0,0));
					jButton7.setEnabled(false);
				}
				{
					jButton8 = new JButton();
					ChessBoard1.add(jButton8);
					jButton8.setBounds(162, 5, 20, 20);
					jButton8.setBackground(new java.awt.Color(255,255,0));
					jButton8.setEnabled(false);
				}
				{
					jButton9 = new JButton();
					ChessBoard1.add(jButton9);
					jButton9.setBounds(241, 5, 20, 20);
					jButton9.setBackground(new java.awt.Color(0,255,0));
					jButton9.setEnabled(false);
				}
				{
					jButton10 = new JButton();
					ChessBoard1.add(jButton10);
					jButton10.setBounds(309, 5, 20, 20);
					jButton10.setBackground(new java.awt.Color(64,0,64));
					jButton10.setEnabled(false);
				}
				{
					jLabel1 = new JLabel();
					ChessBoard1.add(jLabel1);
					jLabel1.setText("Jefferson");
					jLabel1.setBounds(27, 10, 65, 15);
				}
				{
					jLabel2 = new JLabel();
					ChessBoard1.add(jLabel2);
					jLabel2.setText("Bob");
					jLabel2.setBounds(131, 10, 32, 15);
				}
				{
					jLabel3 = new JLabel();
					ChessBoard1.add(jLabel3);
					jLabel3.setText("Alice");
					jLabel3.setBounds(191, 10, 42, 15);
				}
				{
					jLabel4 = new JLabel();
					ChessBoard1.add(jLabel4);
					jLabel4.setText("End");
					jLabel4.setBounds(278, 10, 42, 15);
				}
				ChessBoard1.addMouseListener(new NewMouseListener(){
					private Component ff;

					public void mousePressed(MouseEvent arg0) {
						if(ifset1)
						{
							int a=arg0.getX();
							int b=arg0.getY();
							if(!(a>=31&&a<=491)||(!(b>=31&&b<=491)))
							{
								JOptionPane.showMessageDialog(ff, "Out of Boundary！");return;
							}
							else
							{
								beginj1=((int)(a-31)/22);
								begini1=((int)(b-31)/22);
								a=31+((int)(a-31)/22)*22;
								b=31+((int)(b-31)/22)*22;
								jButton7.setBounds(a, b, 20, 20);
								ifset1=false;
								ifset1can=false;	
								Cango[begini1+1][beginj1+1]=0;
								jButton3.setVisible(true);
							}
														
						}
						if(ifset2)
						{
							int a=arg0.getX();
							int b=arg0.getY();
							if(!(a>=31&&a<=491)||(!(b>=31&&b<=491)))
							{
								JOptionPane.showMessageDialog(ff, "Out of Boundary！");return;
							}
							else
							{
								beginj2=((int)(a-31)/22);
								begini2=((int)(b-31)/22);
								if((beginj2==beginj1)&&(begini2==begini1))
								{JOptionPane.showMessageDialog(ff, "No same spot！");return;}
								a=31+((int)(a-31)/22)*22;
								b=31+((int)(b-31)/22)*22;
								jButton8.setBounds(a, b, 20, 20);
								ifset2=false;
								ifset2can=false;	
								Cango[begini2+1][beginj2+1]=0;
								jButton4.setVisible(true);
							}
						}
						if(ifset3)
						{
							int a=arg0.getX();
							int b=arg0.getY();
							if(!(a>=31&&a<=491)||(!(b>=31&&b<=491)))
							{
								JOptionPane.showMessageDialog(ff, "Out of Boundary！");return;
							}
							else
							{
								beginj3=((int)(a-31)/22);
								begini3=((int)(b-31)/22);
								if(((beginj3==beginj1)&&(begini3==begini1))||((beginj3==beginj2)&&(begini3==begini2)))
								{JOptionPane.showMessageDialog(ff, "No same spot！");return;}
								a=31+((int)(a-31)/22)*22;
								b=31+((int)(b-31)/22)*22;
								jButton9.setBounds(a, b, 20, 20);
								ifset3=false;
								ifset3can=false;	
								Cango[begini3+1][beginj3+1]=0;
								jButton5.setVisible(true);
							}
						}
						if(ifsetend)
						{
							int a=arg0.getX();
							int b=arg0.getY();
							if(!(a>=31&&a<=491)||(!(b>=31&&b<=491)))
							{
								JOptionPane.showMessageDialog(ff, "Out of Boundary！");return;
							}
							else
							{
								endj1=((int)(a-31)/22);
								endi1=((int)(b-31)/22);
								if(((endj1==beginj1)&&(endi1==begini1))||((endj1==beginj2)&&(endi1==begini2))||((endj1==beginj3)&&(endi1==begini3)))
								{
									JOptionPane.showMessageDialog(ff, "No same spot！");
								    return;
								}
								a=31+((int)(a-31)/22)*22;
								b=31+((int)(b-31)/22)*22;
								jButton10.setBounds(a, b, 20, 20);
								ifsetend=false;
								ifsetendcan=false;	
								jButton6.setVisible(true);
								jTextField1.setVisible(true);
							}
						}
						
					}
					
				});
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(527, 391, 108, 22);
				jTextField1.setVisible(false);
				jTextField1.setText("money");
			}
			pack();
			this.setSize(681, 562);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
