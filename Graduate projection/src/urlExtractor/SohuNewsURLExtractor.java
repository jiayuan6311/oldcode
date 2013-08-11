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

public class SohuNewsURLExtractor implements URLExtractor {

		private Filter filter=new Filter();
		//private final String CHARSET=CrawlerConfig.CHARSET;
		
		public SohuNewsURLExtractor(){
			initialize();
		}
		
		public void initialize(){
			filter.appendAccepted("http://news.sohu.com/.*");
			filter.appendDownloaded("http://news.sohu.com/¡£*/n.*");
			filter.appendRejected("http://news.sohu.com/fangtan/.*");
			filter.appendRejected("http://news.sohu.com/dianjijinri/.*");
			filter.appendRejected("http://news.sohu.com/photo/.*");
			filter.appendRejected("http://news.sohu.com/news_flash.*");
			filter.appendRejected("http://news.sohu.com/scroll/.*");
			filter.appendRejected("http://news.sohu.com/upload/.*");
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
