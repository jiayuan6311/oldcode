import java.util.*;
import java.util.regex.Pattern;
import java.lang.reflect.*;

public class Demo {
	private static Pattern p=Pattern.compile("\\w+\\.");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("请输入您需要的资源：");                     // TODO Auto-generated method stub
		System.out.println("需要志愿者请按1：");
		System.out.println("需要武警请按2：");
		System.out.println("需要广播人员请按3：");
		System.out.println("需要医务人员请按4：");
		Scanner input=new Scanner(System.in);
		int s=input.nextInt();
		switch(s){
		case 1:
			Class c=null;
			int num=1;
			try{
				c=Class.forName("Volunteer");
				}catch(ClassNotFoundException e){
					System.out.println("Can't find Volunteer.");
					System.exit(1);
				}
				System.out.println("请输入您想查看的资源信息：");                // TODO Auto-generated method stub
				System.out.println("资源包括的变量有：");
				Method[] m=c.getDeclaredMethods();
				Constructor[] ctor=c.getConstructors();
				Field[] field=c.getDeclaredFields();
				for(int i=0;i<field.length;i++){
					System.out.println(num+". "+p.matcher(field[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("资源包括的构造函数有：");
				for(int i=0;i<ctor.length;i++){
					System.out.println(num+". "+p.matcher(ctor[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("资源包括的方法有有：");
				for(int i=0;i<m.length;i++){
					System.out.println(num+". "+p.matcher(m[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("请输入您想调用信息的编号：");
           		int s1=input.nextInt();
				switch(s1){
				case 1:
				case 2:
				case 3:
					System.out.println("您不能访问私有信息,请重新输入。");
					break;
				case 4:
					System.out.print("志愿者的性别为");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("男。");
					else System.out.print("女。");
					break;
				case 5:
					System.out.print("请输入年龄：");
					int i=input.nextInt();
					System.out.print("请输入姓名：");
					String n=input.next();
					System.out.print("请输入学校名称：");
					String sn=input.next();
					System.out.print("请输入性别：");
					String g=input.next();
					System.out.print("生成志愿者"+n+"，年龄"+i+"，性别"+g+"，来自"+sn+"。");
					break;
				case 6:
					System.out.println("您不能访问私有方法,请重新输入。");
					break;
				case 7:
					System.out.println("getAgeBracket()被成功调用。");
					break;
				case 8:
					System.out.println("请输入目的地。");
					String in=input.next();
					System.out.println("askRoute(String)被成功调用。");
					break;
				case 9:
					System.out.println("请输入目标队伍。");
					String in1=input.next();
					System.out.println("askQueueTime(String)被成功调用。");
					break;
				}
				break;
		case 2:
			Class c2=null;
			int num2=1;
			try{
				c2=Class.forName("Armedpolice");
				}catch(ClassNotFoundException e){
					System.out.println("Can't find Armedpolice.");
					System.exit(1);
				}
				System.out.println("请输入您想查看的资源信息：");                // TODO Auto-generated method stub
				System.out.println("资源包括的变量有：");
				Method[] m2=c2.getDeclaredMethods();
				Constructor[] ctor2=c2.getConstructors();
				Field[] field2=c2.getDeclaredFields();
				for(int i=0;i<field2.length;i++){
					System.out.println(num2+". "+p.matcher(field2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("资源包括的构造函数有：");
				for(int i=0;i<ctor2.length;i++){
					System.out.println(num2+". "+p.matcher(ctor2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("资源包括的方法有有：");
				for(int i=0;i<m2.length;i++){
					System.out.println(num2+". "+p.matcher(m2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("请输入您想调用信息的编号：");
           		int s2=input.nextInt();
				switch(s2){
				case 1:
				case 2:
					System.out.println("您不能访问私有信息,请重新输入。");
					break;
				case 3:
					System.out.print("武警的性别为");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("男。");
					else System.out.print("女。");
					break;
				case 4:
					System.out.print("请输入年龄：");
					int i=input.nextInt();
					System.out.print("请输入姓名：");
					String n=input.next();
					System.out.print("请输入性别：");
					String g=input.next();
					System.out.print("生成武警"+n+"，年龄"+i+"，性别"+g+"。");
					break;
				case 5:
					System.out.println("您不能访问私有方法,请重新输入。");
					break;
				case 6:
					System.out.println("请输入寻求帮助的原因。");
					String in=input.next();
					System.out.println("askforhelp(String)被成功调用。");
					break;
				}
				break;
		case 3:
			Class c3=null;
			int num3=1;
			try{
				c3=Class.forName("Broadcaster");
				}catch(ClassNotFoundException e){
					System.out.println("Can't find Broadcaster.");
					System.exit(1);
				}
				System.out.println("请输入您想查看的资源信息：");                // TODO Auto-generated method stub
				System.out.println("资源包括的变量有：");
				Method[] m3=c3.getDeclaredMethods();
				Constructor[] ctor3=c3.getConstructors();
				Field[] field3=c3.getDeclaredFields();
				for(int i=0;i<field3.length;i++){
					System.out.println(num3+". "+p.matcher(field3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("资源包括的构造函数有：");
				for(int i=0;i<ctor3.length;i++){
					System.out.println(num3+". "+p.matcher(ctor3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("资源包括的方法有有：");
				for(int i=0;i<m3.length;i++){
					System.out.println(num3+". "+p.matcher(m3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("请输入您想调用信息的编号：");
           		int s3=input.nextInt();
				switch(s3){
				case 1:
				case 2:
					System.out.println("您不能访问私有信息,请重新输入。");
					break;
				case 3:
					System.out.print("广播人员的性别为");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("男。");
					else System.out.print("女。");
					break;
				case 4:
					System.out.print("请输入年龄：");
					int i=input.nextInt();
					System.out.print("请输入姓名：");
					String n=input.next();
					System.out.print("请输入性别：");
					String g=input.next();
					System.out.print("生成广播员"+n+"，年龄"+i+"，性别"+g+"。");
					break;
				case 5:
					System.out.println("您不能访问私有方法,请重新输入。");
					break;
				case 6:
					System.out.println("请输入广播内容。");
					String in=input.next();
					System.out.println("BroadcastInfo(String)被成功调用。");
					break;
				}
				break;
		case 4:
			Class c4=null;
			int num4=1;
			try{
				c4=Class.forName("Healthworker");
				}catch(ClassNotFoundException e){
					System.out.println("Can't find Healthworker.");
					System.exit(1);
				}
				System.out.println("请输入您想查看的资源信息：");                // TODO Auto-generated method stub
				System.out.println("资源包括的变量有：");
				Method[] m4=c4.getDeclaredMethods();
				Constructor[] ctor4=c4.getConstructors();
				Field[] field4=c4.getDeclaredFields();
				for(int i=0;i<field4.length;i++){
					System.out.println(num4+". "+p.matcher(field4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("资源包括的构造函数有：");
				for(int i=0;i<ctor4.length;i++){
					System.out.println(num4+". "+p.matcher(ctor4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("资源包括的方法有有：");
				for(int i=0;i<m4.length;i++){
					System.out.println(num4+". "+p.matcher(m4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("请输入您想调用信息的编号：");
           		int s4=input.nextInt();
				switch(s4){
				case 1:
				case 2:
					System.out.println("您不能访问私有信息,请重新输入。");
					break;
				case 3:
					System.out.print("医务人员的性别为");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("男。");
					else System.out.print("女。");
					break;
				case 4:
					System.out.print("请输入年龄：");
					int i=input.nextInt();
					System.out.print("请输入姓名：");
					String n=input.next();
					System.out.print("请输入性别：");
					String g=input.next();
					System.out.print("生成医务人员"+n+"，年龄"+i+"，性别"+g+"。");
					break;
				case 5:
					System.out.println("您不能访问私有方法,请重新输入。");
					break;
				case 6:
					System.out.println("请输入想咨询的症状。");
					String in=input.next();
					System.out.println("Advice(String)被成功调用。");
					break;
				case 7:
					System.out.println("Emergency()被成功调用。");
					break;
				}
				break;
				
		}

	}

}
