package urlExtractor;

import java.util.ArrayList;
import java.util.List;


import config.CrawlerConfig;
import dataStructure.URL;
import dataStructure.WebNode;
import status.URLStatus;
import urlFilter.Filter;
import util.PageUtil;
import util.URLUtil;

public class SinaNewsURLExtractor implements URLExtractor {

		private Filter filter=new Filter();
		//private final String CHARSET=CrawlerConfig.CHARSET;
		
		public SinaNewsURLExtractor(){
			initialize();
		}
		
		public void initialize(){
			filter.appendAccepted("http://news.sina.com.cn.*");
			filter.appendDownloaded("http://news.sina.com.cn/c/.*");
			filter.appendDownloaded("http://news.sina.com.cn/w/.*");
			filter.appendDownloaded("http://news.sina.com.cn/s/.*");
			filter.appendRejected("http://news.sina.com.cn/css/.*");
			filter.appendRejected("http://news.sina.com.cn/iframe/.*");
			filter.appendRejected("http://news.sina.com.cn/js/.*");
			filter.appendRejected("http://news.sina.com.cn/guide/.*");
			filter.appendRejected("http://news.sina.com.cn/pfpnews/.*");
			filter.appendRejected("http://news.sina.com.cn/opinion/.*");
			filter.appendRejected("http://news.sina.com.cn/photo/.*");
			filter.appendRejected("http://news.sina.com.cn/hotnews/.*");
			
		}
		
		public  List<URL> extractAllURLs(WebNode webNode){
			//initialize();
			List<URL> list=new ArrayList<URL>();
			//String url=webNode.getURL();
			URL urlPack;
			String text=webNode.getText();
			List<String> nodes=PageUtil.extractAllValuesWithAttribute(text, "a", "href");
			for(String extractedURL : nodes)			
				{if(extractedURL==null || extractedURL.matches(".*#.*") 
						|| extractedURL.matches(".*javascript.*") || extractedURL.length()<6)
					continue;
				//String host="http://"+URLUtil.getHost(url);
				//if(!extractedURL.matches("http://.*"))
				//	extractedURL=host+extractedURL;
				if(!URLUtil.isLegalURL(extractedURL))
					continue;
				urlPack=new URL(extractedURL);
				urlPack=filter.getStatusWithFilter(urlPack);
				if(urlPack.getStatus()==URLStatus.TO_DOWNLOAD || urlPack.getStatus()==URLStatus.TO_PROCESS)
					list.add(urlPack);
			}
			if (CrawlerConfig.CRAWLER_DOWNLOAD_IMAGE == 1)
				list.addAll(ImageLinkExtractor.extractingAllIMGLinks(webNode
						.getText()));
			return list;

	}

		@Override
		public List<URL> setStatusForURLList(List<URL> list) {
			//initialize();
			List<URL> result=new ArrayList<URL>();
			for(URL e : list){
				URL urlPack=filter.getStatusWithFilter(e);
				if(urlPack.getStatus()!=URLStatus.REJECT)
					result.add(urlPack);
			}
			return result;
		}

}
