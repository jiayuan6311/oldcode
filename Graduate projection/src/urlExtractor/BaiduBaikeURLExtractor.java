package urlExtractor;

import java.util.ArrayList;
import java.util.List;

import config.CrawlerConfig;
import dataStructure.URL;
import dataStructure.WebNode;
import status.LinkType;
import status.URLStatus;
import urlFilter.Filter;
import util.PageUtil;
import util.URLUtil;

public class BaiduBaikeURLExtractor implements URLExtractor {

	public static void main(String[] args) {
	}

	private Filter filter = new Filter();

	// private final String CHARSET=CrawlerConfig.CHARSET;

	public BaiduBaikeURLExtractor() {
		initialize();
	}

	public void initialize() {
		filter.appendAccepted("http://baike.baidu.com.*");
		filter.appendDownloaded("http://baike.baidu.com/view/\\d+.*");
		filter.appendRejected("http://baike.baidu.com/history.*");
		filter.appendRejected("http://baike.baidu.com/edit.*");
		filter.appendRejected("http://baike.baidu.com/update.*");
		filter.appendRejected("http://baike.baidu.com/uc.*");
		filter.appendRejected("http://baike.baidu.com/star.*");
		filter.appendRejected("http://baike.baidu.com/odp.*");
		filter.appendRejected("http://baike.baidu.com/quanwei.*");
		filter.appendRejected("http://baike.baidu.com/cms.*");
		filter.appendRejected("http://baike.baidu.com/page.*");
		filter.appendRejected("http://baike.baidu.com/image.*");
		filter.appendRejected("http://baike.baidu.com/searchword.*");
		filter.appendRejected("http://baike.baidu.com/photo.*");
		filter.appendRejected("http://baike.baidu.com/goodlist.*");
	}

	public List<URL> extractAllURLs(WebNode webNode) {
		// initialize();
		List<URL> list = new ArrayList<URL>();
		String url = webNode.getUrl();
		URL urlPack;
		String text = webNode.getText();
		List<String> nodes = PageUtil.extractAllValuesWithAttribute(text, "a","href");
		for (String extractedURL : nodes) {
			if (extractedURL == null || extractedURL.matches(".*#.*")
					|| extractedURL.matches(".*javascript.*")
					|| extractedURL.length() < 6)
				continue;
			String host = "http://" + URLUtil.getHost(url);
			if (!extractedURL.matches("http://.*"))
				extractedURL = host + extractedURL;
			if (!URLUtil.isLegalURL(extractedURL))
				continue;
			urlPack = new URL(extractedURL);
			urlPack = filter.getStatusWithFilter(urlPack);
			urlPack.setType(LinkType.URL);
			if (urlPack.getStatus() == URLStatus.TO_DOWNLOAD
					|| urlPack.getStatus() == URLStatus.TO_PROCESS) {
				if (urlPack.getStatus() == URLStatus.TO_DOWNLOAD) {
					String tempUrl = URLUtil.cutPattern(urlPack.getURL(), "?");
					tempUrl = URLUtil.htm2html(tempUrl);
					urlPack.setURL(tempUrl);
				}
				list.add(urlPack);
			}
		}
		if (CrawlerConfig.CRAWLER_DOWNLOAD_IMAGE == 1)
			list.addAll(ImageLinkExtractor.extractingAllIMGLinks(webNode
					.getText()));
		return list;
	}

	public List<URL> setStatusForURLList(List<URL> list) {
		// initialize();
		List<URL> result = new ArrayList<URL>();
		for (URL e : list) {
			URL urlPack = filter.getStatusWithFilter(e);
			if (urlPack.getStatus() != URLStatus.REJECT)
				result.add(urlPack);
		}
		return result;
	}
}
