import java.util.*;

public class Demo {
     
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int sig=1;
        Smap smap=new Smap();
		do {
        int s11;  
	    System.out.println("���������");
		System.out.println("����1:����һ��ѧ���ɼ� ");
		System.out.println("����2:ɾ��һ��ѧ���ɼ� ");
		System.out.println("����3:����id��ѯѧ���ɼ�");
		System.out.println("����4:��������ѧ���ɼ����ֵܷ�ƽ���������������߷֣���ͷ֣����������Σ�90-100,80-89,70-79,60-69��0-59��������");
		Scanner input=new Scanner(System.in);
		s11=input.nextInt();
		
		switch(s11){
		case 1:
			int id=0,ps=0,gs=0;
		    char sex='F';
		    String name="",major="";
		    	System.out.println("�����¼���ѧ��id");
				id=input.nextInt();
				System.out.println("����ѧ������");
				name=input.next();
				System.out.println("����ѧ���Ա�M/F��");
				sex=input.next().charAt(0);
				System.out.println("����ѧ��רҵ");
				major=input.next();
				System.out.println("����ѧ��������ҵ������0-45��");
				ps=input.nextInt();
				if(ps<0||ps>45){
					System.out.println("������0-45֮��ĸ��˷���!");
					break;
				}
			    System.out.println("����ѧ���Ŷ���ҵ������0-50��");
				gs=input.nextInt();
				if(gs<0||gs>50){
					System.out.println("������0-50֮����Ŷ���ҵ����!");
					break;
				}
		        Student st1=new Student(id,name,sex,major,ps,gs);
		        smap.putnew(st1);
		        System.out.println("�ɹ����룡");
		        System.out.println("������"+smap.getone(id).getName());
				System.out.println("�Ա�"+smap.getone(id).getSex());
				System.out.println("רҵ��"+smap.getone(id).getMajor());
				//ts=smap.getone(id2).getGs()+smap.getone(id2).getPs()+smap.getone(id2).getSs();
				System.out.println("�ܷ���"+smap.getone(id).getTs());
		        break;
		case 2:
			int id1=0;
			System.out.println("������ɾ��ѧ��id");
			id1=input.nextInt();
			smap.deleteone(id1);
			System.out.println("�ɹ�ɾ����");
			break;
		case 3:
			int id2=0;
			System.out.println("�������ѯѧ��id");
			id2=input.nextInt();
			System.out.println("������"+smap.getone(id2).getName());
			System.out.println("�Ա�"+smap.getone(id2).getSex());
			System.out.println("רҵ��"+smap.getone(id2).getMajor());
			//ts=smap.getone(id2).getGs()+smap.getone(id2).getPs()+smap.getone(id2).getSs();
			System.out.println("�ܷ���"+smap.getone(id2).getTs());
			break;
		case 4:
			smap.fenbu();
		}
		System.out.println("�Ƿ��˳�����y/n��");
		if(input.next()=="y"){
			sig=0;
		}
	}while(sig==1);
	}
}


