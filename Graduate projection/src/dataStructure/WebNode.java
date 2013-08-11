package dataStructure;

import config.CrawlerConfig;
import util.PageUtil;

public class WebNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private String project;
	private String url;
	private String title;
	private String text;
	private String comeFrom;
	private String author;
	private String time;
	private int linkType;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int type) {
		if (type >= 3)
			linkType = 0;
		else
			linkType = type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public WebNode(String url, String title, String text) {
		this.url = url;
		this.title = title;
		this.text = text;
		this.project = CrawlerConfig.PROJECT_NAME;
	}

	public WebNode(String project, String url, String title, String text) {
		this.project = project;
		this.url = url;
		this.title = title;
		this.text = text;
	}

	public WebNode(String project, String url, String title, String text,
			String comeFrom, String author, String time) {
		this.project = project;
		this.url = url;
		this.title = title;
		this.text = text;
		this.author=author;
		this.time = time;
		this.comeFrom = comeFrom;
	}

	public WebNode() {

	}

	public String toString() {
		return "<Project>" + project + "</Project>" + "\n<URL>" + url
				+ "</URL>" + "\n<Title>" + title + "</Title>" + "\n<Author>"
				+ author + "</Author>" + "\n<ComeFrom>" + comeFrom
				+ "</ComeFrom>" + "\n<Time>" + time + "</Time>"
				+ "\n<Content>\n" + text + "</Content>\n";
	}

	public static WebNode parseWebNode(String page) {
		return new WebNode(PageUtil
				.parseTagWithoutAttribute(page, "Project", 0), PageUtil
				.parseTagWithoutAttribute(page, "URL", 0), PageUtil
				.parseTagWithoutAttribute(page, "Title", 0), PageUtil
				.parseTagWithoutAttribute(page, "Content", 0));
	}
}
