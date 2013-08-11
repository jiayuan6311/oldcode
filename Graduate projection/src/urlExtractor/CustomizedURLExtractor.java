package urlExtractor;

import java.util.ArrayList;
import java.util.List;

import config.CrawlerConfig;
import config.CustomizedURLFilter;
import dataStructure.URL;
import dataStructure.WebNode;
import status.URLStatus;
import urlFilter.Filter;
import util.PageUtil;
import util.URLUtil;

public class CustomizedURLExtractor implements URLExtractor{

	private Filter filter=new Filter();
	//private final String CHARSET=CrawlerConfig.CHARSET;
	
	public CustomizedURLExtractor(){
		initialize();
	}
	
	@Override
	public List<URL> extractAllURLs(WebNode webNode) {
		//initialize();
		List<URL> list=new ArrayList<URL>();
		String url=webNode.getUrl();
		URL urlPack;
		String text=webNode.getText();
		//System.out.println(text);
		List<String> nodes=PageUtil.extractAllValuesWithAttribute(text, "a", "href");
		for(String extractedURL : nodes)			
			{if(extractedURL==null || extractedURL.matches(".*#.*") 
					|| extractedURL.matches(".*javascript.*") || extractedURL.length()<6)
				continue;
			String host=CustomizedURLFilter.HOST_ADD;
			//String host="http://"+URLUtil.getHost(url)+"/";;
			if(host==null || host.equals("null") || host.equals(""))
				host="http://"+URLUtil.getHost(url)+"/";
			else{
				if(!host.contains("http://"))
					host="http://"+host;
			}
			if(!extractedURL.matches("http://.*"))
			{
				while(extractedURL.charAt(0)=='.')
					extractedURL=extractedURL.substring(1, extractedURL.length());
				extractedURL=host+extractedURL;
			}
			//System.out.println(extractedURL);
			if(CrawlerConfig.ORIGIN_CHARSET!=null)
				extractedURL=URLUtil.ChineseCharacters2ISN(extractedURL, CrawlerConfig.ORIGIN_CHARSET);
			extractedURL=CharacterCorrecter.correctErrorCode(extractedURL);
			extractedURL=extractedURL.toLowerCase();
			if(extractedURL.contains("shop"))
				System.out.println(extractedURL);
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

	public void initialize(){
		List<String> acceptedList=CustomizedURLFilter.ACCEPTED_PATTERNS;
		List<String> downloadedList=CustomizedURLFilter.DOWNLOAD_PATTERNS;
		List<String> rejectedList=CustomizedURLFilter.REJECTED_PATTERNS;
		for(String accept : acceptedList)
			filter.appendAccepted(accept);
		for(String download : downloadedList)
			filter.appendDownloaded(download);
		for(String reject : rejectedList)
			filter.appendRejected(reject);
	}
	
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
