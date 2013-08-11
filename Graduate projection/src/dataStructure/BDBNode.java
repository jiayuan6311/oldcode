package dataStructure;

public class BDBNode {

	private String key;
	private String data;
	
	public BDBNode(String key, String data){
		this.key=key;
		this.data=data;
	}
	
	public BDBNode(){
		key=null;
		data=null;
	}
	
	public void setKey(String key){
		this.key=key;
	}
	
	public void setData(String data){
		this.data=data;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getData(){
		return data;
	}
}
