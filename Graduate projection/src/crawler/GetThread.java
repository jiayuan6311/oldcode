package crawler;

import dataStructure.URL;
import util.HttpClientUtil;

public class GetThread extends Thread {
	private URL url;
	private Object mutex = "mutex";

	public GetThread(URL url) {
		this.url = url;
	}

	public void run() {
		if(!Frontier.alreadyProcessed.contains(url.getURL())){
			Frontier.processedUrlDB.put(url.getURL(), String.valueOf(url.getStatus()));
			CrawlingThread thread=new CrawlingThread(HttpClientUtil.createHttpClient(), url);
			synchronized(mutex){
				Frontier.threads.add(thread);
				Frontier.alreadyProcessed.add(url.getURL());
			}
		}
	}
}
