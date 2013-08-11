package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import config.CrawlerConfig;
import config.LogConfig;

public class Logger {
	
	public static void reset(){
		StringBuffer sb=new StringBuffer(LogConfig.LOG_HOME);
		sb.append("\\");
		sb.append(CrawlerConfig.PROJECT_NAME);
		sb.append(".log");
		String logName=sb.toString();
		File logDir=new File(LogConfig.LOG_HOME);
		if(!logDir.exists())
			logDir.mkdirs();
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(logName));
			//bw.write(log);
			//bw.newLine();
			//bw.flush();==
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void info(String log){
		StringBuffer sb=new StringBuffer(LogConfig.LOG_HOME);
		sb.append("\\");
		sb.append(CrawlerConfig.PROJECT_NAME);
		sb.append(".log");
		String logName=sb.toString();
		File logDir=new File(LogConfig.LOG_HOME);
		if(!logDir.exists())
			logDir.mkdirs();
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(logName, true));
			bw.write(log);
			bw.newLine();
			bw.flush();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initialize(){
		
	}
	
}
