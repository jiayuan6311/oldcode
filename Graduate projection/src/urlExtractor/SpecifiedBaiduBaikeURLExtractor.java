package urlExtractor;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import config.CrawlerConfig;
import dataStructure.URL;
import dataStructure.WebNode;
import status.LinkType;
import status.URLStatus;
import util.PageUtil;

public class SpecifiedBaiduBaikeURLExtractor implements URLExtractor {

	public static void main(String[] args) {
		String htmlPage = PageUtil.readFile("D:\\Test\\test.html");
		String encoding = PageUtil.getEncoding(htmlPage);
		Parser parser = Parser.createParser(htmlPage, encoding);

		String classRegex = "http://baike.baidu.com/class.*.html";
		String taglistRegex = "http://baike.baidu.com/taglist\\?tag=.*";
		String endRegex = "http://baike.baidu.com/view/.*\\?fromTaglist";

		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e) {
			// return null;
		}
		String host = "http://baike.baidu.com";
		NodeList nodelist = page.getBody();
		NodeFilter filter = new TagNameFilter("a");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);

		for (int i = 0; i < nodelist.size(); i++) {
			LinkTag link = (LinkTag) nodelist.elementAt(i);
			String url = link.getAttribute("href");
			if (url == null || url.equals(""))
				continue;

			if (!url.matches("http://baike.baidu.com/.*"))
				url = host + url;

			if (!url.matches(endRegex) && !url.matches(taglistRegex)
					&& !url.matches(classRegex))
				continue;
			System.out.println(url);
		}
	}

	@Override
	public List<URL> extractAllURLs(WebNode webNode) {
		String url = webNode.getUrl();

		String classRegex = "http://baike.baidu.com/class.*.html";
		String taglistRegex = "http://baike.baidu.com/taglist\\?tag=.*";
		String endRegex = "http://baike.baidu.com/view/.*\\?fromTaglist";
		/*if (url.matches(endRegex)) {
			return null;
		}*/
		String host = "http://baike.baidu.com";
		String title = webNode.getTitle();
		if(title==null)
			title=PageUtil.extractTitle(webNode.getTitle());

		List<URL> urls = new ArrayList<URL>();
		String htmlPage = webNode.getText();
		String encoding = PageUtil.getEncoding(htmlPage);
		Parser parser = Parser.createParser(htmlPage, encoding);

		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e) {
			return null;
		}
		NodeList nodelist = page.getBody();
		NodeFilter filter = new TagNameFilter("a");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);

		for (int i = 0; i < nodelist.size(); i++) {
			LinkTag link = (LinkTag) nodelist.elementAt(i);
			String tmpURL = link.getAttribute("href");
			if (tmpURL == null || tmpURL.equals(""))
				continue;
			if (!tmpURL.matches("http://baike.baidu.com/.*"))
				tmpURL = host + tmpURL;
			//System.out.println(tmpURL);
			if (!tmpURL.matches(endRegex) && !tmpURL.matches(taglistRegex)
					&& !tmpURL.matches(classRegex))
				continue;
			URL urlPack = new URL();
			urlPack.setURL(tmpURL);
			urlPack.setType(LinkType.URL);

			if (url.matches(classRegex)) {
				if (!tmpURL.matches(taglistRegex))
					continue;
				urlPack.setStatus(URLStatus.TO_PROCESS);
				urls.add(urlPack);
				continue;
			}
			if (url.matches(taglistRegex)) {
				if(tmpURL.matches(endRegex)){
					urlPack.setStatus(URLStatus.TO_DOWNLOAD);
					urlPack.setTitle(title);
					urls.add(urlPack);
					continue;
				}else if(tmpURL.matches(taglistRegex)){
					urlPack.setStatus(URLStatus.TO_PROCESS);
					urlPack.setTitle(null);
					urls.add(urlPack);
					continue;
				}
				//if (tmpURL.matches(endRegex)) continue;
				
			}
			if (url.matches(endRegex)) {
				if (CrawlerConfig.CRAWLER_DOWNLOAD_IMAGE == 1)
					urls.addAll(ImageLinkExtractor
							.extractingAllIMGLinks(webNode.getText()));
			}
		}
		return urls;
	}

	@Override
	public List<URL> setStatusForURLList(List<URL> list) {
		return null;
	}

}
