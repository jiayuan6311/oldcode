import java.util.*;

public class Smap{
	public static int num=20;
	private Map<Integer,Student> smap=new HashMap<Integer,Student>();
	Smap(){
		Student.initialize();
		for(int i=1;i<=num;i++){
			smap.put(Student.slist[i].getId(),Student.slist[i]);
		}
	}
	public void putnew(Student s1){
		num+=1;
		smap.put(s1.getId(),s1);
	}
	public void deleteone(int id){
		num-=1;
		smap.remove(id);
	}
    public Student getone(int id){
    	return smap.get(id);        //??
    }
    public double getaverange(){
    	int sum;
    	sum=0;
        Set<Integer> set=smap.keySet();
        for(int i:set){
        	sum+=smap.get(i).getTs();
    	}
        double num1=num;
        return sum/num1;
    }
    public void fenbu(){
    	Set<Integer> set=smap.keySet();
    	int max=smap.get(1).getTs(),min=smap.get(1).getTs();
    	String maxn=null,minn = null;
    	int[] a1=new int[5];
    	Arrays.fill(a1,0);
    	for(int i:set){
    		if((0<=smap.get(i).getTs())&&(smap.get(i).getTs()<=59)){
    			a1[0]+=1;
    		}
    		else if((60<=smap.get(i).getTs())&&(smap.get(i).getTs()<=69)){
    			a1[1]+=1;
    		}
    		else if((70<=smap.get(i).getTs())&&(smap.get(i).getTs()<=79)){
    			a1[2]+=1;
    		}
    		else if((80<=smap.get(i).getTs())&&(smap.get(i).getTs()<=89)){
    			a1[3]+=1;
    		}
    		else if((90<=smap.get(i).getTs())&&(smap.get(i).getTs()<=100)){
    			a1[4]+=1;
    		}
    		if(smap.get(i).getTs()>max){
    			max=smap.get(i).getTs();
    			maxn=smap.get(i).getName();
    		}
    		if(smap.get(i).getTs()<min){
    			min=smap.get(i).getTs();
    			minn=smap.get(i).getName();
    		}
    	}
    		System.out.println("�õ���ͷ�������"+minn);
    		System.out.println("���ķ�����"+min);
    		System.out.println("�õ���߷�������"+maxn);
    		System.out.println("���ķ�����"+max);
    		
    	System.out.println("������0-59��ѧ�������ǣ�"+a1[0]);
    	System.out.println("������60-69��ѧ�������ǣ�"+a1[1]);
    	System.out.println("������70-79��ѧ�������ǣ�"+a1[2]);
    	System.out.println("������80-89��ѧ�������ǣ�"+a1[3]);
    	System.out.println("������90-100��ѧ�������ǣ�"+a1[4]);
    	System.out.println("ƽ�������ǣ�"+getaverange());
    }
    
    
}
