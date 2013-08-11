package urlExtractor;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import dataStructure.URL;
import status.LinkType;
import status.URLStatus;
import util.PageUtil;
import util.URLUtil;

public class ImageLinkExtractor {

	public static void main(String[] main) {
		String htmlPage = PageUtil.readFile("D:\\Test\\test.html");
		List<URL> urls = extractingAllIMGLinks(htmlPage);
		for (URL url : urls) {
			System.out.println(url.getURL() + "\t" + url.getFolder() + "\t\t"
					+ url.getStatus() + "\t" + url.getTitle());
		}
	}

	public static List<URL> extractingAllIMGLinks(String htmlPage) {
		List<URL> links = new ArrayList<URL>();
		String encoding = PageUtil.getEncoding(htmlPage);
		String folder = PageUtil.extractTitle(htmlPage);
		if (folder == null)
			return null;
		Parser parser = Parser.createParser(htmlPage, encoding);
		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e) {

		}
		NodeList nodelist = page.getBody();
		NodeFilter filter = new TagNameFilter("img");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);
		for (int i = 0; i < nodelist.size(); i++) {
			ImageTag link = (ImageTag) nodelist.elementAt(i);
			String title = link.getAttribute("title");

			String url = link.getAttribute("src");
			if(!URLUtil.isLegalURL(url))
				continue;
			if (url == null || endsWithSpecifiedExtensions(url) == null)
				continue;
			if (title == null) {
				int j = url.length() - 1;
				while (j >= 0 && url.charAt(j) != '\\' && url.charAt(j) != '/')
					j--;
				title = url.substring(j + 1);
			}
			if (title == null)
				title = url;
			URL urlPack = new URL(url);
			urlPack.setFolder(folder);
			urlPack.setStatus(URLStatus.TO_DOWNLOAD);
			urlPack.setTitle(title);
			urlPack.setType(LinkType.IMAGE);
			boolean contain = false;
			if (links.size() != 0) {
				for (URL u : links) {
					if (u.getURL().equals(url)) {
						contain = true;
						break;
					}
				}
			}
			if (contain == false)
				links.add(urlPack);
		}
		/*for(URL u : links){
			System.out.println(u.getFolder()+"\t"+u.getURL()+"\t"+u.getTitle()+"\t"+u.getType());
		}*/
		return links;
	}

	public static String endsWithSpecifiedExtensions(String link) {
		if (link == null)
			return null;
		String[] imageExtensions = { "jpg", "jpeg", "png", "gif", "jfif",
				"tif", "tiff", "bmp", "dib" };
		link = link.toLowerCase();
		String extension = null;
		for (String e : imageExtensions) {
			if (link.endsWith(e)) {
				extension = e;
				break;
			}
		}
		return extension;
	}
}
