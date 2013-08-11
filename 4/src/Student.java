import java.util.*;
public class Student {
	public static Student[] slist=new Student[21];
	private int id,ps,gs,ss,ts;
	private char sex;
	private String name,major;
	public Student(int i1,char s,String maj){
		Random rd1=new Random();
		setId(i1);
		setSs(5);
		name="N";
		setSex(s);
		setPs(rd1.nextInt(46));
		setGs(rd1.nextInt(51));
		setMajor(maj);
		setTs(ps+gs+ss);
	}                                
	public Student(int i1,String n,char s,String maj,int ps,int gs){
		setId(i1);
		setPs(ps);
		setGs(gs);
		setSs(5);
		name=n;
		setSex(s);
		setMajor(maj);
		setTs(ps+gs+ss);
	}                              //两种构造函数
	
	public static void initialize(){
		for(int i=1;i<=9;i++){
			slist[i]=new Student(i,'M',"CS");
			slist[i].name+=i;
		    }
		slist[10]=new Student(10,'F',"CS");
	    slist[10].name+=10;
	    for(int j=11;j<=20;j++){
	    	slist[j]=new Student(j,'M',"EE");
		    slist[j].name+=j;
		    }
	    for(int c=16;c<=20;c++){
		    slist[c]=new Student(c,'F',"EE");
		    slist[c].name+=c;
		    }
	   // slist[1].setPs(20);slist[2].setPs(29);slist[3].setPs(31);slist[4].setPs(33);slist[5].setPs(44);slist[6].setPs(5);slist[7].setPs(40);slist[8].setPs(13);slist[9].setPs(37);slist[10].setPs(3);slist[11].setPs(18);slist[12].setPs(2);
	  //  slist[13].setPs(4);slist[14].setPs(28);slist[15].setPs(35);slist[16].setPs(16);slist[17].setPs(18);slist[18].setPs(31);slist[10].setPs(19);slist[20].setPs(38);
	    
	   // slist[1].setGs(10);slist[2].setGs(17);slist[3].setGs(41);slist[4].setGs(19);slist[5].setGs(42);slist[6].setGs(15);slist[7].setGs(7);slist[8].setGs(49);slist[9].setGs(44);slist[10].setGs(46);slist[11].setGs(15);slist[12].setGs(20);slist[13].setGs(21);
	  //  slist[14].setGs(16);slist[15].setGs(33);slist[16].setGs(17);slist[17].setGs(18);slist[18].setGs(5);slist[19].setGs(36);slist[20].setGs(11); 	
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getPs() {
		return ps;
	}
	public void setGs(int gs) {
		this.gs = gs;
	}
	public int getGs() {
		return gs;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public char getSex() {
		return sex;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajor() {
		return major;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public int getTs() {
		return ts;
	}
	public void setSs(int ss) {
		this.ss = ss;
	}
	public int getSs() {
		return ss;
	}
	public String getName() {
		return name;
	}

}
