import java.util.*;
import java.util.regex.Pattern;
import java.lang.reflect.*;

public class Demo {
	private static Pattern p=Pattern.compile("\\w+\\.");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("����������Ҫ����Դ��");                     // TODO Auto-generated method stub
		System.out.println("��Ҫ־Ը���밴1��");
		System.out.println("��Ҫ�侯�밴2��");
		System.out.println("��Ҫ�㲥��Ա�밴3��");
		System.out.println("��Ҫҽ����Ա�밴4��");
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
				System.out.println("����������鿴����Դ��Ϣ��");                // TODO Auto-generated method stub
				System.out.println("��Դ�����ı����У�");
				Method[] m=c.getDeclaredMethods();
				Constructor[] ctor=c.getConstructors();
				Field[] field=c.getDeclaredFields();
				for(int i=0;i<field.length;i++){
					System.out.println(num+". "+p.matcher(field[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("��Դ�����Ĺ��캯���У�");
				for(int i=0;i<ctor.length;i++){
					System.out.println(num+". "+p.matcher(ctor[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("��Դ�����ķ������У�");
				for(int i=0;i<m.length;i++){
					System.out.println(num+". "+p.matcher(m[i].toString()).replaceAll(""));
					num++;
				}
				System.out.println("���������������Ϣ�ı�ţ�");
           		int s1=input.nextInt();
				switch(s1){
				case 1:
				case 2:
				case 3:
					System.out.println("�����ܷ���˽����Ϣ,���������롣");
					break;
				case 4:
					System.out.print("־Ը�ߵ��Ա�Ϊ");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("�С�");
					else System.out.print("Ů��");
					break;
				case 5:
					System.out.print("���������䣺");
					int i=input.nextInt();
					System.out.print("������������");
					String n=input.next();
					System.out.print("������ѧУ���ƣ�");
					String sn=input.next();
					System.out.print("�������Ա�");
					String g=input.next();
					System.out.print("����־Ը��"+n+"������"+i+"���Ա�"+g+"������"+sn+"��");
					break;
				case 6:
					System.out.println("�����ܷ���˽�з���,���������롣");
					break;
				case 7:
					System.out.println("getAgeBracket()���ɹ����á�");
					break;
				case 8:
					System.out.println("������Ŀ�ĵء�");
					String in=input.next();
					System.out.println("askRoute(String)���ɹ����á�");
					break;
				case 9:
					System.out.println("������Ŀ����顣");
					String in1=input.next();
					System.out.println("askQueueTime(String)���ɹ����á�");
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
				System.out.println("����������鿴����Դ��Ϣ��");                // TODO Auto-generated method stub
				System.out.println("��Դ�����ı����У�");
				Method[] m2=c2.getDeclaredMethods();
				Constructor[] ctor2=c2.getConstructors();
				Field[] field2=c2.getDeclaredFields();
				for(int i=0;i<field2.length;i++){
					System.out.println(num2+". "+p.matcher(field2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("��Դ�����Ĺ��캯���У�");
				for(int i=0;i<ctor2.length;i++){
					System.out.println(num2+". "+p.matcher(ctor2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("��Դ�����ķ������У�");
				for(int i=0;i<m2.length;i++){
					System.out.println(num2+". "+p.matcher(m2[i].toString()).replaceAll(""));
					num2++;
				}
				System.out.println("���������������Ϣ�ı�ţ�");
           		int s2=input.nextInt();
				switch(s2){
				case 1:
				case 2:
					System.out.println("�����ܷ���˽����Ϣ,���������롣");
					break;
				case 3:
					System.out.print("�侯���Ա�Ϊ");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("�С�");
					else System.out.print("Ů��");
					break;
				case 4:
					System.out.print("���������䣺");
					int i=input.nextInt();
					System.out.print("������������");
					String n=input.next();
					System.out.print("�������Ա�");
					String g=input.next();
					System.out.print("�����侯"+n+"������"+i+"���Ա�"+g+"��");
					break;
				case 5:
					System.out.println("�����ܷ���˽�з���,���������롣");
					break;
				case 6:
					System.out.println("������Ѱ�������ԭ��");
					String in=input.next();
					System.out.println("askforhelp(String)���ɹ����á�");
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
				System.out.println("����������鿴����Դ��Ϣ��");                // TODO Auto-generated method stub
				System.out.println("��Դ�����ı����У�");
				Method[] m3=c3.getDeclaredMethods();
				Constructor[] ctor3=c3.getConstructors();
				Field[] field3=c3.getDeclaredFields();
				for(int i=0;i<field3.length;i++){
					System.out.println(num3+". "+p.matcher(field3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("��Դ�����Ĺ��캯���У�");
				for(int i=0;i<ctor3.length;i++){
					System.out.println(num3+". "+p.matcher(ctor3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("��Դ�����ķ������У�");
				for(int i=0;i<m3.length;i++){
					System.out.println(num3+". "+p.matcher(m3[i].toString()).replaceAll(""));
					num3++;
				}
				System.out.println("���������������Ϣ�ı�ţ�");
           		int s3=input.nextInt();
				switch(s3){
				case 1:
				case 2:
					System.out.println("�����ܷ���˽����Ϣ,���������롣");
					break;
				case 3:
					System.out.print("�㲥��Ա���Ա�Ϊ");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("�С�");
					else System.out.print("Ů��");
					break;
				case 4:
					System.out.print("���������䣺");
					int i=input.nextInt();
					System.out.print("������������");
					String n=input.next();
					System.out.print("�������Ա�");
					String g=input.next();
					System.out.print("���ɹ㲥Ա"+n+"������"+i+"���Ա�"+g+"��");
					break;
				case 5:
					System.out.println("�����ܷ���˽�з���,���������롣");
					break;
				case 6:
					System.out.println("������㲥���ݡ�");
					String in=input.next();
					System.out.println("BroadcastInfo(String)���ɹ����á�");
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
				System.out.println("����������鿴����Դ��Ϣ��");                // TODO Auto-generated method stub
				System.out.println("��Դ�����ı����У�");
				Method[] m4=c4.getDeclaredMethods();
				Constructor[] ctor4=c4.getConstructors();
				Field[] field4=c4.getDeclaredFields();
				for(int i=0;i<field4.length;i++){
					System.out.println(num4+". "+p.matcher(field4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("��Դ�����Ĺ��캯���У�");
				for(int i=0;i<ctor4.length;i++){
					System.out.println(num4+". "+p.matcher(ctor4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("��Դ�����ķ������У�");
				for(int i=0;i<m4.length;i++){
					System.out.println(num4+". "+p.matcher(m4[i].toString()).replaceAll(""));
					num4++;
				}
				System.out.println("���������������Ϣ�ı�ţ�");
           		int s4=input.nextInt();
				switch(s4){
				case 1:
				case 2:
					System.out.println("�����ܷ���˽����Ϣ,���������롣");
					break;
				case 3:
					System.out.print("ҽ����Ա���Ա�Ϊ");
					Random rd=new Random();
					if(rd.nextBoolean()) System.out.print("�С�");
					else System.out.print("Ů��");
					break;
				case 4:
					System.out.print("���������䣺");
					int i=input.nextInt();
					System.out.print("������������");
					String n=input.next();
					System.out.print("�������Ա�");
					String g=input.next();
					System.out.print("����ҽ����Ա"+n+"������"+i+"���Ա�"+g+"��");
					break;
				case 5:
					System.out.println("�����ܷ���˽�з���,���������롣");
					break;
				case 6:
					System.out.println("����������ѯ��֢״��");
					String in=input.next();
					System.out.println("Advice(String)���ɹ����á�");
					break;
				case 7:
					System.out.println("Emergency()���ɹ����á�");
					break;
				}
				break;
				
		}

	}

}
