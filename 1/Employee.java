/*
 * Created on 2010-7-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*;
public class Employee {
    private int age, id;
    private String name;
	private char gender;
    public Employee(int i,char g,int aa)
    {
    	name="ABC";
    	age=aa;
    	id=i;
    	gender=g;
    }
    public static Employee[] elist=new Employee[21];
	public static void main(String[] args) throws   IOException 
	{
		String s="";
		for(int j=0;j<10;j++)
	    {
			elist[j]=new Employee(j,'M',30+j);
			elist[j].name+=String.valueOf(j);
	    }
	    for(int j=10;j<=20;j++)
	    {
	    	elist[j]=new Employee(j,'F',10+j);
	    	elist[j].name="AB"+String.valueOf(j);
	    }
		try
		{
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("please input your order:");
			s=input.readLine();
		//	System.out.println(s);
		}
		catch(IOException aaa){;}
		if (s.contains("select")&&s.contains("where"))             
		{
			String s1=s.substring(s.indexOf("select"),s.indexOf("where"));
			String s2=s.substring(s.indexOf("where")); //从where分成两段
			int[] a1={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			int[] a2={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			int[] a3={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			if(s2.contains("and"))  //条件分析开始，包含and的情况
			{
				String s21=s2.substring(s2.indexOf("where"),s2.indexOf("and"));
				String s22=s2.substring(s2.indexOf("and"));  //and条件的两端
				if(s21.contains("$age")){      
					if (s21.contains("<="))
					{
						int ind=s21.indexOf("<=")+2;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<=a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains("<")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf("<")+1;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains("="))
					{
						int ind=s21.indexOf("=")+1;
					    String age1=s21.substring(ind, ind+2);
					    int a=Integer.parseInt(age1);
					    for(int j=0;j<=20;j++)
					{
						if (elist[j].age==a) 
							{
							a1[j]=1;
							}
				     }
					}
					else if(s21.contains(">=")){
						int ind=s21.indexOf(">=")+2;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>=a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains(">")&&(!s21.contains("="))){
						int ind=s21.indexOf(">")+1;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>a) 
								{
								a1[j]=1;
								}
						}
					}
		        }
				if(s21.contains("$id")){
					if (s21.contains("<="))
					{
						int ind=s21.indexOf("<=")+2;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<=i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains(">="))
					{
						int ind=s21.indexOf(">=")+2;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>=i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains("<")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf("<")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains(">")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf(">")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains("="))
					{
						int ind=s21.indexOf("=")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id==i) 
								{
								a1[j]=1;
								}
						}
					}
				}
				if(s21.contains("$name")){
					int ind=s21.indexOf("=")+1;
                    String name1=s21.substring(ind, ind+4);
					for(int j=0;j<=20;j++)
					{
						if (elist[j].name.equals(name1))
							{
							a1[j]=1;
							}
					}
				}
				if(s21.contains("$gender")){
					int ind=s21.indexOf("=")+1;
                    String g1=s21.substring(ind);
                    char[] g=g1.toCharArray();
					for(int j=0;j<=20;j++)
					{
						if (elist[j].gender==g[0]) 
							{
							a1[j]=1;
							}
					}	
				}                                            //and的第一个条件检验
				if(s22.contains("$age")){     
					if (s22.contains("<="))
					{
						int ind=s22.indexOf("<=")+2;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<=a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains("<")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf("<")+1;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains("="))
					{
						int ind=s22.indexOf("=")+1;
					    String age1=s22.substring(ind, ind+2);
					    int a=Integer.parseInt(age1);
					    for(int j=0;j<=20;j++)
					{
						if (elist[j].age==a) 
							{
							a2[j]=1;
							}
				     }
					}
					else if(s22.contains(">=")){
						int ind=s22.indexOf(">=")+2;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>=a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains(">")&&(!s22.contains("="))){
						int ind=s22.indexOf(">")+1;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>a) 
								{
								a2[j]=1;
								}
						}
					}
		        }
				if(s22.contains("$id")){
					if (s22.contains("<="))
					{
						int ind=s22.indexOf("<=")+2;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<=i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains(">="))
					{
						int ind=s22.indexOf(">=")+2;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>=i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains("<")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf("<")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains(">")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf(">")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains("="))
					{
						int ind=s22.indexOf("=")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id==i) 
								{
								a2[j]=1;
								}
						}
					}
				}
				if(s22.contains("$name")){
					int ind=s22.indexOf("=")+1;
                    String name1=s22.substring(ind, ind+4);
					for(int j=0;j<=20;j++)
					{
						if (elist[j].name.equals(name1)) 
							{
							a2[j]=1;
							}
					}
				}
				if(s22.contains("$gender")){
					int ind=s22.indexOf("=")+1;
                    String g1=s22.substring(ind);
                    char[] g=g1.toCharArray();
					for(int j=0;j<=20;j++)
					{
						if (elist[j].gender==g[0]) 
							{
							a2[j]=1;
							}
					}	
				}                                             //and的第二个条件检查
				for(int i=0;i<=20;i++){
					a3[i]=a1[i]*a2[i];
				}                                             //and两个条件的结合
			}
			else if(s2.contains("or"))        //包含or的情况
			{
				String s21=s2.substring(s2.indexOf("where"),s2.indexOf("or"));
				String s22=s2.substring(s2.indexOf("or"));  //or条件的两端
				if(s21.contains("$age")){      
					if (s21.contains("<="))
					{
						int ind=s21.indexOf("<=")+2;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<=a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains("<")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf("<")+1;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains("="))
					{
						int ind=s21.indexOf("=")+1;
					    String age1=s21.substring(ind, ind+2);
					    int a=Integer.parseInt(age1);
					    for(int j=0;j<=20;j++)
					{
						if (elist[j].age==a) 
							{
							a1[j]=1;
							}
				     }
					}
					else if(s21.contains(">=")){
						int ind=s21.indexOf(">=")+2;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>=a) 
								{
								a1[j]=1;
								}
						}
					}
					else if(s21.contains(">")&&(!s21.contains("="))){
						int ind=s21.indexOf(">")+1;
						String age1=s21.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>a) 
								{
								a1[j]=1;
								}
						}
					}
		        }
				if(s21.contains("$id")){
					if (s21.contains("<="))
					{
						int ind=s21.indexOf("<=")+2;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<=i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains(">="))
					{
						int ind=s21.indexOf(">=")+2;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>=i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains("<")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf("<")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains(">")&&(!s21.contains("=")))
					{
						int ind=s21.indexOf(">")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>i) 
								{
								a1[j]=1;
								}
						}
					}
					if (s21.contains("="))
					{
						int ind=s21.indexOf("=")+1;
						String id1=s21.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id==i) 
								{
								a1[j]=1;
								}
						}
					}
				}
				if(s21.contains("$name")){
					int ind=s21.indexOf("=")+1;
                    String name1=s21.substring(ind, ind+4);
					for(int j=0;j<=20;j++)
					{
						if (elist[j].name.equals(name1))
							{
							a1[j]=1;
							}
					}
				}
				if(s21.contains("$gender")){
					int ind=s21.indexOf("=")+1;
                    String g1=s21.substring(ind);
                    char[] g=g1.toCharArray();
					for(int j=0;j<=20;j++)
					{
						if (elist[j].gender==g[0]) 
							{
							a1[j]=1;
							}
					}	
				}                                            //or的第一个条件检验
				if(s22.contains("$age")){     
					if (s22.contains("<="))
					{
						int ind=s22.indexOf("<=")+2;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<=a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains("<")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf("<")+1;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains("="))
					{
						int ind=s22.indexOf("=")+1;
					    String age1=s22.substring(ind, ind+2);
					    int a=Integer.parseInt(age1);
					    for(int j=0;j<=20;j++)
					{
						if (elist[j].age==a) 
							{
							a2[j]=1;
							}
				     }
					}
					else if(s22.contains(">=")){
						int ind=s22.indexOf(">=")+2;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>=a) 
								{
								a2[j]=1;
								}
						}
					}
					else if(s22.contains(">")&&(!s22.contains("="))){
						int ind=s22.indexOf(">")+1;
						String age1=s22.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>a) 
								{
								a2[j]=1;
								}
						}
					}
		        }
				if(s22.contains("$id")){
					if (s22.contains("<="))
					{
						int ind=s22.indexOf("<=")+2;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<=i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains(">="))
					{
						int ind=s22.indexOf(">=")+2;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>=i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains("<")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf("<")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains(">")&&(!s22.contains("=")))
					{
						int ind=s22.indexOf(">")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>i) 
								{
								a2[j]=1;
								}
						}
					}
					if (s22.contains("="))
					{
						int ind=s22.indexOf("=")+1;
						String id1=s22.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id==i) 
								{
								a2[j]=1;
								}
						}
					}
				}
				if(s22.contains("$name")){
					int ind=s22.indexOf("=")+1;
                    String name1=s22.substring(ind, ind+4);
					for(int j=0;j<=20;j++)
					{
						if (elist[j].name.equals(name1))
							{
							a2[j]=1;
							}
					}
				}
				if(s22.contains("$gender")){
					int ind=s22.indexOf("=")+1;
                    String g1=s22.substring(ind);
                    char[] g=g1.toCharArray();
					for(int j=0;j<=20;j++)
					{
						if (elist[j].gender==g[0]) 
							{
							a2[j]=1;
							}
					}	
				}                                             //or的第二个条件检查
				for(int i=0;i<=20;i++){
					a3[i]=a1[i]+a2[i];                       //or两个条件的结合
				} 
			}
			else if(s2.contains("$")){                            //不包括and,or
				if(s2.contains("$age")){      //只有age条件
					if (s2.contains("<="))
					{
						int ind=s2.indexOf("<=")+2;
						String age1=s2.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<=a) 
								{
								a3[j]=1;
								}
						}
					}
					else if(s2.contains("<")&&(!s2.contains("=")))
					{
						int ind=s2.indexOf("<")+1;
						String age1=s2.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age<a) 
								{
								a3[j]=1;
								}
						}
					}
					else if(s2.contains("="))
					{
						int ind=s2.indexOf("=")+1;
					    String age1=s2.substring(ind, ind+2);
					    int a=Integer.parseInt(age1);
					    for(int j=0;j<=20;j++)
					{
						if (elist[j].age==a) 
							{
							a3[j]=1;
							}
				     }
					}
					else if(s2.contains(">=")){
						int ind=s2.indexOf(">=")+2;
						String age1=s2.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>=a) 
								{
								a3[j]=1;
								}
						}
					}
					else if(s2.contains(">")&&(!s2.contains("="))){
						int ind=s2.indexOf(">")+1;
						String age1=s2.substring(ind, ind+2);
						int a=Integer.parseInt(age1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].age>a) 
								{
								a3[j]=1;
								}
						}
					}
		        }
				if(s2.contains("$id")){
					if (s2.contains("<="))
					{
						int ind=s2.indexOf("<=")+2;
						String id1=s2.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<=i) 
								{
								a3[j]=1;
								}
						}
					}
					if (s2.contains(">="))
					{
						int ind=s2.indexOf(">=")+2;
						String id1=s2.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>=i) 
								{
								a3[j]=1;
								}
						}
					}
					if (s2.contains("<")&&(!s2.contains("=")))
					{
						int ind=s2.indexOf("<")+1;
						String id1=s2.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id<i) 
								{
								a3[j]=1;
								}
						}
					}
					if (s2.contains(">")&&(!s2.contains("=")))
					{
						int ind=s2.indexOf(">")+1;
						String id1=s2.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id>i) 
								{
								a3[j]=1;
								}
						}
					}
					if (s2.contains("="))
					{
						int ind=s2.indexOf("=")+1;
						String id1=s2.substring(ind, ind+2);
						int i=Integer.parseInt(id1);
						for(int j=0;j<=20;j++)
						{
							if (elist[j].id==i) 
								{
								a3[j]=1;
								}
						}
					}
				}
				if(s2.contains("$name")){
					int ind=s2.indexOf("=")+1;
                    String name1=s2.substring(ind, ind+4);
					for(int j=0;j<=20;j++)
					{
						if (elist[j].name.equals(name1)) 
							{
							a3[j]=1;
							}
					}
				}
				if(s2.contains("$gender")){
					int ind=s2.indexOf("=")+1;
                    String g1=s2.substring(ind);
                    char[] g=g1.toCharArray();
					for(int j=0;j<=20;j++)
					{
						if (elist[j].gender==g[0]) 
							{
							a3[j]=1;
							}
					}	
				}
			}else{
				for(int i=0;i<=20;i++){
					a3[i]=1;
				}
		}                                                   //条件检测结束
			for(int i=0;i<=20;i++){
				if(a3[i]!=0){
				    if(s1.contains("$id"))
					{System.out.print(elist[i].id);
					 System.out.print("    ");
					}
				    if(s1.contains("$name"))
				    {System.out.print(elist[i].name);
				    System.out.print("     ");
				    }
				    if(s1.contains("$gender"))
				    {System.out.print(elist[i].gender);
				    System.out.print("     ");
				    }
				    if(s1.contains("$age"))
				    {System.out.print(elist[i].age);}
				     System.out.println();	
			    }
			}
		}			
		else
		{
			System.out.println("Wrong order");
			
		}                                            
	}
}