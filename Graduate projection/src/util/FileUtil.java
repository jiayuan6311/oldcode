package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {

	private static ArrayList<String> fileList=new ArrayList<String>();
	private static int count=0;
	
	public static void main(String[] args) {
		System.out.println(FileUtil.getFileCount("D:\\Eclipse\\Workspace\\WebCrawler\\data\\fetchedPage"));
	}

	public static void listFiles(String directory){
		File dir=new File(directory);
		if(dir.exists()){
			File[] files=dir.listFiles();
			for(File f : files){
				if(f.isDirectory())
					listFiles(f.getAbsolutePath());
				else
					fileList.add(f.getAbsolutePath());
			}
		}
	}
	
	public static String getText(String filename){
		try {
			BufferedReader br=new BufferedReader(new FileReader(filename));
			String temp=br.readLine();
			StringBuffer sb=new StringBuffer("");
			while(temp!=null){
				sb.append(temp);
				sb.append("\n");
				temp=br.readLine();
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String parseFileName(String filename){
		char[] excluded={'\\','/','?',':','<','>','|','*','"'};
		int index=-1;
		for(char ch : excluded){
			index=filename.indexOf(ch);
			while(index>=0){
			if(index==0 && filename.length()==1)
				return null;
			if(index>0)
				filename=filename.substring(0, index)+filename.substring(index+1, filename.length());
			else if(index==0)
				filename=filename.substring(1, filename.length());
			index=filename.indexOf(ch);
		}
		}
		return filename;
	}
	
	public static ArrayList<String> getFileList(String directory){
		listFiles(directory);
		return fileList;
	}
	
	public static void writeToTxt(String destFile, String text){
		try {
			
			BufferedWriter bw=new BufferedWriter(new FileWriter(destFile));
			bw.write(text);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTxtFile(String destFile){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(destFile));
			bw.write("");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void countingFiles(String directory){
		File f=new File(directory);
		if(!f.exists())
			return;
		File[] files=f.listFiles();
		for(File file : files){
			if(file.isDirectory())
				countingFiles(file.getAbsolutePath());
			else
				count++;
		}
	}
	
	public static int getFileCount(String directory){
		count=0;
		countingFiles(directory);
		return count;
	}
	
	public static void appendToTxt(String destFile, String text){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(destFile, true));
			bw.write(text);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printFileList(String directory){
		ArrayList<String> list=getFileList(directory);
		for(String s : list){
			System.out.println(s);
		}
	}
}
