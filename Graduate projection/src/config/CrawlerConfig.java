package config;

import java.util.List;

import util.ConfigUtil;

public class CrawlerConfig {
	public static final String PROJECT_NAME=ConfigUtil.getStringProperty("crawler.project_name"); 
	public static final int MAX_THREAD_QUANTITY=ConfigUtil.getIntegerProperty("crawler.max_thread_quantity");
	public static final String DATA_HOME=ConfigUtil.getStringProperty("crawler.data.home");
	public static final String CHARSET=ConfigUtil.getStringProperty("crawler.charset");
	public static String ORIGIN_CHARSET=null;
	public static final List<String> URL_SEEDS=ConfigUtil.getListProperity("crawler.URL_seeds");
	public static final int WEBSITE_SELECTION=ConfigUtil.getIntegerProperty("crawler.website");
	public static final long POLITENESS_DELAY=ConfigUtil.getLongProperty("crawler.politeness_delay");
	public static final int RETRY_TIMES=ConfigUtil.getIntegerProperty("crawler.retry_times");
	public static final int CRAWLER_TYPE=0;
	public static final int CHANNEL=0;
	public static final String CRAWLER_COMEFROM=ConfigUtil.getStringProperty("crawler.comefrom");
	public static final int CRAWLER_DOWNLOAD_IMAGE=ConfigUtil.getIntegerProperty("crawler.downloadImage");
	
	public static final boolean getOriginalHtml(){
		if(ConfigUtil.getIntegerProperty("crawler.origin_html")==1)
			return true;
		return false;
	}
	
	public static final boolean getParsedHtml(){
		if(ConfigUtil.getIntegerProperty("crawler.parse_text")==1)
			return true;
		return false;
	}
	
	public static final boolean createIndex(){
		if(ConfigUtil.getIntegerProperty("crawler.index.create")==1)
			return true;
		return false;
	}
}
