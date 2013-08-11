package crawler;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import config.BDBConfig;
import config.CrawlerConfig;
import dataStructure.URL;
import dataStructure.WebNode;
import logger.Logger;
import status.HTTPStatus;
import status.LinkType;
import status.URLStatus;
import textExtractor.BaiduBaikeTextExtractor;
import textExtractor.CustomizedTextExtractor;
import textExtractor.HudongBaikeTextExtractor;
import textExtractor.SinaNewsTextExtractor;
import textExtractor.SohuNewsTextExtractor;
import textExtractor.TextExtractor;

import urlExtractor.BaiduBaikeURLExtractor;
import urlExtractor.CustomizedURLExtractor;
import urlExtractor.SinaNewsURLExtractor;
import urlExtractor.SohuNewsURLExtractor;

import urlExtractor.HudongBaikeURLExtractor;

import urlExtractor.URLExtractor;
import util.PageUtil;
import util.Time;

public class CrawlingThread extends Thread {
	private final DefaultHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpGet;
	private final URL urlPack;
	private WebNode webNode = null;
	private boolean fail = false;
	public String errorInfo = null;
	private Object mutex = "mutex";
	private Object delayMutex = "delayMutex";
	private boolean retry = false;
	private double singleLength = 0;
	private static URLExtractor urlExtractor;
	private static TextExtractor textExtractor;

	public CrawlingThread(DefaultHttpClient httpClient, URL urlPack) {
		this.httpClient = httpClient;
		this.urlPack = urlPack;
		this.httpGet = new HttpGet(urlPack.getURL());
		this.context = new BasicHttpContext();
		extractorInitialize();

	}

	public void run() {

		this.crawling();
		int retrytimes = 1;
		while (retry == true && retrytimes < Frontier.retryTimes) {
			this.retryCrawling();
			retrytimes++;
		}
		if (retrytimes == Frontier.retryTimes)
			log();
		httpClient.getConnectionManager().shutdown();
	}

	public void crawling() {
		try {
			synchronized (delayMutex) {
				long now = Time.getLongCurrentTime();
				while ((now - Frontier.lastFetchTime) < CrawlerConfig.POLITENESS_DELAY) {
					Thread.sleep(100);
					now = Time.getLongCurrentTime();
				}
				Frontier.lastFetchTime = Time.getLongCurrentTime();
			}

			HttpResponse response = httpClient.execute(httpGet, context);
			if (urlExtractor == null || textExtractor == null) {
				Logger.info("URLExtractor或TextExtractor初始化失败，请检查配置文件！");
				return;
			}
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND) {
					errorInfo = response.getStatusLine().toString()
							+ ", while fetching ";
				} else

					errorInfo = HTTPStatus.getHTTPStatus(response
							.getStatusLine().getStatusCode());
				fail = true;
			}

			else {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					byte[] bytes = EntityUtils.toByteArray(entity);
					if (urlPack.getType() == LinkType.URL) {
						String charset = CrawlerConfig.ORIGIN_CHARSET;
						String page;
						if (CrawlerConfig.ORIGIN_CHARSET == null) {
							page = new String(bytes, "UTF-8");
							charset = PageUtil.getEncoding(page).toUpperCase();
							CrawlerConfig.ORIGIN_CHARSET = charset;
						}

						page = new String(bytes, charset);
						singleLength = page.length() / 1024;
						String url = httpGet.getURI().toString();
						webNode = new WebNode(url, PageUtil.extractTitle(page),
								page);
						
						if (urlPack.getStatus() == URLStatus.TO_PROCESS
									|| urlPack.getStatus() == URLStatus.TO_DOWNLOAD) {
								List<URL> urlList = urlExtractor
										.extractAllURLs(webNode);
								if (urlList!=null && !urlList.isEmpty())
									appendURLList(urlList);
							}
						

						if (urlPack.getStatus() == URLStatus.TO_DOWNLOAD) {
							if (CrawlerConfig.getOriginalHtml()) {

								if (webNode != null)
									Frontier.writeOriginalHTMLToFile(webNode);

							}
							if (CrawlerConfig.getParsedHtml()) {

								webNode = textExtractor.extractText(webNode);
								if (webNode != null)
									Frontier.writeToFileAndIndex(webNode);
							}
						}
					} else if (urlPack.getType() == LinkType.IMAGE) {
						Frontier.saveImageAs(bytes, urlPack.getFolder(),
								urlPack.getTitle(), urlPack.getURL());
					}
				} else {
					if(urlPack.getType()!=LinkType.IMAGE){
						fail = true;
						errorInfo = "HttpEntity Error";
					}
				}
			}
		} catch (Exception ex) {
			if(urlPack.getType()!=LinkType.IMAGE){
				fail = true;
				retry = true;
				errorInfo = "Http response error!\n";
				System.out.println(errorInfo);
			}
			this.httpGet.abort();
		}
		if (retry == false)
			log();

	}

	public void retryCrawling() {
		try {
			synchronized (delayMutex) {
				long now = Time.getLongCurrentTime();
				while ((now - Frontier.lastFetchTime) < CrawlerConfig.POLITENESS_DELAY) {
					Thread.sleep(100);
					now = Time.getLongCurrentTime();
				}
				Frontier.lastFetchTime = Time.getLongCurrentTime();
			}
			HttpResponse response = httpClient.execute(httpGet, context);
			if (urlExtractor == null || textExtractor == null) {
				Logger
						.info("Failed to initialize URL extrator or text extractor");
				return;
			}
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND) {
					errorInfo = response.getStatusLine().toString()
							+ ", while fetching ";
				} else

					errorInfo = HTTPStatus.getHTTPStatus(response
							.getStatusLine().getStatusCode());
				fail = true;
			}

			else {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					byte[] bytes = EntityUtils.toByteArray(entity);
					if (urlPack.getType() == LinkType.URL) {
						String charset = CrawlerConfig.ORIGIN_CHARSET;
						String page;
						if (CrawlerConfig.ORIGIN_CHARSET == null
								|| !charset
										.equals(CrawlerConfig.ORIGIN_CHARSET)) {
							page = new String(bytes, "UTF-8");
							charset = PageUtil.getEncoding(page).toUpperCase();
							CrawlerConfig.ORIGIN_CHARSET = charset;
						}

						page = new String(bytes, charset);
						singleLength = page.length() / 1024;
						String url = httpGet.getURI().toString();
						webNode = new WebNode(url, null, page);

					 
							if (urlPack.getStatus() == URLStatus.TO_PROCESS
									|| urlPack.getStatus() == URLStatus.TO_DOWNLOAD) {
								List<URL> urlList = urlExtractor
										.extractAllURLs(webNode);
								if (urlList!=null && !urlList.isEmpty())
									appendURLList(urlList);
							}
						

						// 页面是否需要下载
						if (urlPack.getStatus() == URLStatus.TO_DOWNLOAD) {
							if (CrawlerConfig.getOriginalHtml()) {


								webNode = textExtractor.extractText(webNode);
								if (webNode != null)
									Frontier.writeOriginalHTMLToFile(webNode);

							}
							if (CrawlerConfig.getParsedHtml()) {
	
								webNode = textExtractor.extractText(webNode);
								if (webNode != null)
									Frontier.writeToFileAndIndex(webNode);
							}
						}
						retry = false;
					} else if (urlPack.getType() == LinkType.IMAGE) {
						Frontier.saveImageAs(bytes, urlPack.getFolder(),
								urlPack.getTitle(), urlPack.getURL());
						retry=false;
					}
				} else {
					fail = true;
					errorInfo = "HttpEntity Error";
					retry = true;
				}
			}

		} catch (Exception ex) {

		}
		if (retry == false)
			log();
	}

	public void extractorInitialize() {
	
			switch (CrawlerConfig.WEBSITE_SELECTION) {
			case 0:
				urlExtractor = new CustomizedURLExtractor();
				textExtractor = new CustomizedTextExtractor();
				break;
			case 1:
				urlExtractor = new BaiduBaikeURLExtractor();
				textExtractor = new BaiduBaikeTextExtractor();
				break;
			case 2:
				urlExtractor = new HudongBaikeURLExtractor();
				textExtractor = new HudongBaikeTextExtractor();
				break;
			case 3:
				urlExtractor = new SinaNewsURLExtractor();
				textExtractor = new SinaNewsTextExtractor();
				break;
			case 4:
				urlExtractor = new SohuNewsURLExtractor();
				textExtractor = new SohuNewsTextExtractor();
				break;
			}
		
	}

	public synchronized void appendURLList(List<URL> urlList) {
		int tailerNum = Integer.parseInt(Frontier.pendingUrlDB
				.get(Frontier.TAILER));
		for (URL e : urlList) {
			if (!Frontier.alreadyProcessed.contains(e.getURL())) {
				tailerNum++;
				Frontier.pendingUrlDB.put(String.valueOf(tailerNum), e
						.toString());
			}
		}
		Frontier.pendingUrlDB.put(Frontier.TAILER, String.valueOf(tailerNum));
	}

	public synchronized void appendURLNode(URL e) {
		int tailerNum = Integer.parseInt(Frontier.pendingUrlDB
				.get(Frontier.TAILER));
		if (!Frontier.alreadyProcessed.contains(e.getURL())) {
			tailerNum++;
			Frontier.pendingUrlDB.put(String.valueOf(tailerNum), e.toString());
		}
		Frontier.pendingUrlDB.put(Frontier.TAILER, String.valueOf(tailerNum));
	}

	public synchronized void log() {
		Frontier.totalDownloadedKB += singleLength;
		if (webNode != null)
			Frontier.successDownloadCount++;

		long now = Time.getLongCurrentTime();
		if ((now - Frontier.lastSyncTime) > BDBConfig.DB_FLUSH_INTERVAL) {
//			if (CrawlerConfig.createIndex())
//				Frontier.index.commit();
			Frontier.lastSyncTime = Time.getLongCurrentTime();
			Frontier.processedUrlDB.sync();
			Frontier.pendingUrlDB.sync();
		}

		synchronized (mutex) {
			Frontier.processCount++;
			Logger.info(Time.getStringCurrentTime());
			Logger.info(urlPack.getURL());
			System.out.println(Time.getStringCurrentTime() + " "
					+ Frontier.processCount + " " + urlPack.getURL() + "\n");
			if (fail == true) {
				Frontier.failedCount++;
				Logger.info("失败，失败原因: " + errorInfo);
			} else
				Logger.info("成功!");
			Logger.info(this.logCount());
		}
	}

	private String logCount() {
		StringBuffer sb = new StringBuffer("");
		sb.append("处理页面: ");
		sb.append(Frontier.processCount);
		sb.append("; 下载页面: ");
		sb.append(Frontier.successDownloadCount);
		sb.append("; 失败数: ");
		sb.append(Frontier.failedCount);
		sb.append("; 剩余URL: ");
		int remaining = Integer.parseInt(Frontier.pendingUrlDB
				.get(Frontier.TAILER))
				- Integer.parseInt(Frontier.pendingUrlDB.get(Frontier.HEADER))
				+ 1;
		sb.append(remaining);
		sb.append("; 下载总量: ");
		DecimalFormat df = new DecimalFormat("#.000");
		if (Frontier.totalDownloadedKB / 1024 < 1) {
			sb.append(Frontier.totalDownloadedKB);
			sb.append("KB.");
		} else if (Frontier.totalDownloadedKB / (1024 * 1024) < 1) {
			double dbl = Frontier.totalDownloadedKB / 1024;
			sb.append(df.format(dbl));
			sb.append("MB.");
		} else if (Frontier.totalDownloadedKB / (1024 * 1024 * 1024) < 1) {
			double dbl = Frontier.totalDownloadedKB / (1024 * 1024);
			sb.append(df.format(dbl));
			sb.append("GB.");
		}

		sb.append("\n\n");
		return sb.toString();
	}

}
