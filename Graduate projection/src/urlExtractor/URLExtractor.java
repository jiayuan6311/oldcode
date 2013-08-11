package urlExtractor;

import java.util.List;

import dataStructure.URL;
import dataStructure.WebNode;



public interface URLExtractor {
	public  List<URL> extractAllURLs(WebNode webNode);
	public  List<URL> setStatusForURLList(List<URL> list);
}
