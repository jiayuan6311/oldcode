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
	    System.out.println("请输入命令：");
		System.out.println("输入1:插入一个学生成绩 ");
		System.out.println("输入2:删除一个学生成绩 ");
		System.out.println("输入3:根据id查询学生成绩");
		System.out.println("输入4:计算所有学生成绩的总分的平均分数，并输出最高分，最低分，各个分数段（90-100,80-89,70-79,60-69，0-59）的人数");
		Scanner input=new Scanner(System.in);
		s11=input.nextInt();
		
		switch(s11){
		case 1:
			int id=0,ps=0,gs=0;
		    char sex='F';
		    String name="",major="";
		    	System.out.println("输入新加入学生id");
				id=input.nextInt();
				System.out.println("输入学生姓名");
				name=input.next();
				System.out.println("输入学生性别（M/F）");
				sex=input.next().charAt(0);
				System.out.println("输入学生专业");
				major=input.next();
				System.out.println("输入学生个人作业分数（0-45）");
				ps=input.nextInt();
				if(ps<0||ps>45){
					System.out.println("请输入0-45之间的个人分数!");
					break;
				}
			    System.out.println("输入学生团队作业分数（0-50）");
				gs=input.nextInt();
				if(gs<0||gs>50){
					System.out.println("请输入0-50之间的团队作业分数!");
					break;
				}
		        Student st1=new Student(id,name,sex,major,ps,gs);
		        smap.putnew(st1);
		        System.out.println("成功加入！");
		        System.out.println("姓名："+smap.getone(id).getName());
				System.out.println("性别："+smap.getone(id).getSex());
				System.out.println("专业："+smap.getone(id).getMajor());
				//ts=smap.getone(id2).getGs()+smap.getone(id2).getPs()+smap.getone(id2).getSs();
				System.out.println("总分数"+smap.getone(id).getTs());
		        break;
		case 2:
			int id1=0;
			System.out.println("输入想删除学生id");
			id1=input.nextInt();
			smap.deleteone(id1);
			System.out.println("成功删除！");
			break;
		case 3:
			int id2=0;
			System.out.println("输入想查询学生id");
			id2=input.nextInt();
			System.out.println("姓名："+smap.getone(id2).getName());
			System.out.println("性别："+smap.getone(id2).getSex());
			System.out.println("专业："+smap.getone(id2).getMajor());
			//ts=smap.getone(id2).getGs()+smap.getone(id2).getPs()+smap.getone(id2).getSs();
			System.out.println("总分数"+smap.getone(id2).getTs());
			break;
		case 4:
			smap.fenbu();
		}
		System.out.println("是否退出？（y/n）");
		if(input.next()=="y"){
			sig=0;
		}
	}while(sig==1);
	}
}


