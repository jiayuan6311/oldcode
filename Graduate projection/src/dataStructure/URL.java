package dataStructure;

import status.LinkType;
import util.PageUtil;

public class URL {

	private String url;
	private int status; 
	private int type;
	private String title;
	
	//主要指图片所在文件夹名称
	private String folder;
	
	public static void main(String[] args){
	}
	
	public URL(){
		url=null;
		status=0;
	}
	
	/*public URL(String url, int status, int type){
		this.status=status;
		this.url=url;
		this.type=type;
	}*/
	
	public URL(String url){
		this.url=url;
		status=0;
		type=LinkType.URL;
		folder="null";
		title="null";
	}
	
	public void setFolder(String folder){
		this.folder=folder;
	}
	
	public String getFolder(){
		return folder;
	}
	
	public void setURL(String url){
		this.url=url;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setStatus(int status){
		this.status=status;
	}
	
	public int getStatus(){
		return status;
	}
	public String getURL(){
		return url;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type=type;
	}
	
	public String toString(){
		return "<URL>"+this.url+"</URL>"+"\n<Folder>"+folder+"</Folder>\n<Title>"+title+"</Title>\n<Status>"+this.status+"</Status>"+"\n<Type>"+type+"</Type>";
	}
	
	public static URL parseURL(String page){
		URL url= new URL(PageUtil.parseTagWithoutAttribute(page, "URL", 0));
		url.setType(Integer.parseInt(PageUtil.parseTagWithoutAttribute(page, "Type", 0)));
		url.setStatus(Integer.parseInt(PageUtil.parseTagWithoutAttribute(page, "Status", 0)));
		url.setFolder(PageUtil.parseTagWithoutAttribute(page, "Folder", 0));
		url.setTitle(PageUtil.parseTagWithoutAttribute(page, "Title", 0));
		return url;
	}
}
