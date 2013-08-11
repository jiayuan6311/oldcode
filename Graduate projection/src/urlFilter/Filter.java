package urlFilter;

import java.util.ArrayList;
import java.util.List;

import dataStructure.URL;
import status.URLStatus;

public class Filter {
	public  List<String> rejectedPatterns=new ArrayList<String>();
	private List<String> downloadedPatterns=new ArrayList<String>();
	private List<String> acceptedPatterns=new ArrayList<String>();

	
	public void appendRejected(String e){
		if(!rejectedPatterns.contains(e))
			rejectedPatterns.add(e);
	}
	
	public void appendDownloaded(String e){
		if(!downloadedPatterns.contains(e))
			downloadedPatterns.add(e);
	}
	
	public void appendAccepted(String e){
		if(!acceptedPatterns.contains(e))
			acceptedPatterns.add(e);
	}
	
	public URL getStatusWithFilter(URL urlPack){
		String url=urlPack.getURL().toLowerCase();
		if(rejectedPatterns.size()!=0){
			for(String pattern: rejectedPatterns){
				if(url.matches(pattern.toLowerCase()))
				{
					urlPack.setStatus(URLStatus.REJECT);
					return urlPack;
				}
			}
		}
		
		if(downloadedPatterns.size()!=0){
			for(String pattern: downloadedPatterns){
				if(url.matches(pattern.toLowerCase()))
				{
					urlPack.setStatus(URLStatus.TO_DOWNLOAD);
					return urlPack;
				}
			}
		}
		
		if(acceptedPatterns.size()!=0){
			for(String pattern: acceptedPatterns){
				if(url.matches(pattern.toLowerCase())){
					urlPack.setStatus(URLStatus.TO_PROCESS);
					return urlPack;
				}
			}
		}
		
		if(urlPack.getStatus()==URLStatus.UNKNOWN)
			urlPack.setStatus(URLStatus.REJECT);
		return urlPack;
	}
}
