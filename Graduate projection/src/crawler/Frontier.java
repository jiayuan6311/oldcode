package crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import config.CrawlerConfig;
import config.SearcherConfig;
import dataStructure.BDBNode;
import dataStructure.Queue;
import dataStructure.URL;
import dataStructure.WebNode;
import logger.Logger;
//import searcher.Index;
import status.URLStatus;
import urlExtractor.ImageLinkExtractor;
import util.BDB;
import util.FileUtil;
import util.Time;
import util.URLUtil;
import status.*;

/*
 * 完成包括数据库，日志文件各种初始化。
 */

public class Frontier extends Thread {

	public static final String PROCESSED_DB = CrawlerConfig.PROJECT_NAME
			+ "_PROCESSED";
	public static final String HEADER = "header";
	public static final String TAILER = "tailer";
	public static final String CURSOR = "cursor";
	public static final String PENDING_DB = CrawlerConfig.PROJECT_NAME
			+ "_PENDING";
	public static Queue<URL> pending = new Queue<URL>();
	public static BDB processedUrlDB = new BDB();
	public static BDB pendingUrlDB = new BDB();

	
	public static List<String> alreadyProcessed = new ArrayList<String>();

	
	
	public static final String DATA_HOME = CrawlerConfig.DATA_HOME + "\\"
			+ CrawlerConfig.PROJECT_NAME;
	public static final String ORIGINAL_HTML_DATA_HOME = DATA_HOME
			+ "(fullHTML)";
	public static final String IMAGE_DATA_HOME = DATA_HOME + "(image)";
	public static long timeConsumption = Time.getLongCurrentTime();
	public static final int retryTimes = CrawlerConfig.RETRY_TIMES;
	public static double totalDownloadedKB = 0;
	public static int processCount = 0;
	public static int successDownloadCount = 0;
	public static int pendingCount = 0;
	public static int failedCount = 0;
	public static int imageFolderCount = 0;
	public static int unrepeative = 0;
	public static int unrepeativeForHTML = 0;
	public static long lastSyncTime = 0;
	public static long lastFetchTime = 0;
	public static int activeCount = 0;
	public static int fileCount = 0;
	public static int originalFileCount = 0;
	public static String charset = null;

	public static List<CrawlingThread> threads;

	public static void initialize() {

		Logger.info(Time.getStringCurrentTime() + " 正在初始化...");
		System.out.println(Time.getStringCurrentTime() + " 正在初始化...");
		File dataHome = new File(CrawlerConfig.DATA_HOME);
		if (!dataHome.exists())
			dataHome.mkdirs();
		processedUrlDB.openDatabase(PROCESSED_DB);
		List<BDBNode> processedNodeList = processedUrlDB.listAllData();
		for (BDBNode e : processedNodeList)
			alreadyProcessed.add(e.getKey());
		Logger.info(Time.getStringCurrentTime() + " 数据库读取成功！");
		System.out.println(Time.getStringCurrentTime() + " 数据库读取成功！");
		pendingUrlDB.openDatabase(PENDING_DB);
		String headerValue = pendingUrlDB.get(HEADER);
		String tailerValue = pendingUrlDB.get(TAILER);

		if (headerValue == null && tailerValue == null) {
			List<String> tmpSeeds = CrawlerConfig.URL_SEEDS;

			if (tmpSeeds.size() == 0) {
				Logger.info(
						Time.getStringCurrentTime() + " 初始化失败.\n请输入URL种子\n");
				return;
			}
			List<String> urlSeeds = new ArrayList<String>();
			for (String e : tmpSeeds) {
				String utfURL = URLUtil.ChineseCharacters2ISN(e, "UTF-8");
				String gb23URL = URLUtil.ChineseCharacters2ISN(e, "GB2312");
				String gbkURL = URLUtil.ChineseCharacters2ISN(e, "GBK");
				urlSeeds.add(utfURL);
				if (!gb23URL.equals(utfURL))
					urlSeeds.add(gb23URL);
				if (!gbkURL.equals(utfURL) && !gbkURL.equals(gb23URL))
					urlSeeds.add(gbkURL);
			}
			pendingUrlDB.put(HEADER, "0");
			pendingUrlDB.put(TAILER, "0");
			pendingUrlDB.put(CURSOR, "0");
			int i;
			for (i = 0; i < urlSeeds.size(); i++) {
				if (processedUrlDB.Contains(urlSeeds.get(i)))
					continue;
				URL u = new URL(urlSeeds.get(i));
				u.setStatus(URLStatus.TO_PROCESS);
				u.setType(LinkType.URL);
				pendingUrlDB.put(String.valueOf(i), u.toString());
			}
			pendingUrlDB.put(TAILER, String.valueOf(i - 1));
			pendingUrlDB.put(CURSOR, String.valueOf(i));
		}

		int headerNum = Integer.parseInt(pendingUrlDB.get(HEADER));
		int tailerNum = Integer.parseInt(pendingUrlDB.get(TAILER));
		if (headerNum != tailerNum || (headerNum == 0 && tailerNum == 0)) {
			int cursorNum = headerNum;
			int increment = 0;
			while (cursorNum <= tailerNum && increment < 3000) {
				URL fromDB = URL.parseURL(pendingUrlDB.get(String
						.valueOf(cursorNum)));
				pending.enQueue(fromDB);
				cursorNum++;
				increment++;
			}
			pendingUrlDB.put(CURSOR, String.valueOf(cursorNum));
		}
		if (lastSyncTime == 0)
			lastSyncTime = Time.getLongCurrentTime();
//		fileCountInitialize();
//		if (CrawlerConfig.createIndex())
//			indexWriterInitialize();

		Logger.info(Time.getStringCurrentTime() + " 初始化成功!\n");
		System.out.println(Time.getStringCurrentTime() + " 初始化成功！\n");
	}



	public static void fileCountInitialize() {
		if (CrawlerConfig.getParsedHtml()) {
			File f = new File(DATA_HOME);
			if (!f.exists())
				f.mkdirs();
			fileCount = FileUtil.getFileCount(DATA_HOME);
		}
		if (CrawlerConfig.getOriginalHtml()) {
			File f = new File(ORIGINAL_HTML_DATA_HOME);
			if (!f.exists())
				f.mkdirs();
			originalFileCount = FileUtil.getFileCount(ORIGINAL_HTML_DATA_HOME);
		}
	}

	public static void multiThreads() {

		while (!pending.isEmpty()) {
			List<CrawlingThread> threadList = Frontier.batchCrawlingThreads();
			for (int i = 0; i < threadList.size(); i++) {
				threadList.get(i).start();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}

			for (int i = 0; i < threadList.size(); i++) {
				try {
					threadList.get(i).join();
				} catch (InterruptedException e) {

				}
			}
			int tailerNum = Integer.parseInt(pendingUrlDB.get(TAILER));
			int cursorNum = Integer.parseInt(pendingUrlDB.get(CURSOR));

			if (pending.size() < 3000) {
				int increment = 0;
				while (cursorNum <= tailerNum && increment < 3000) {
					URL fromDB = URL.parseURL(pendingUrlDB.get(String
							.valueOf(cursorNum)));
					pending.enQueue(fromDB);
					cursorNum++;
					increment++;
				}
				pendingUrlDB.put(CURSOR, String.valueOf(cursorNum));
			}
			if (pending.isEmpty()) {
				String over = Time.getStringCurrentTime() + " 爬虫结束！\n";
				over += "耗时："
						+ String
								.valueOf((Time.getLongCurrentTime() - Frontier.timeConsumption) / 60000)
						+ "分钟；共处理页面：" + String.valueOf(Frontier.processCount)
						+ "；共下载页面："
						+ String.valueOf(Frontier.successDownloadCount) + "。\n";
				System.out.println(over);
				Logger.info(over);
			}
		}
	}

	private static List<CrawlingThread> batchCrawlingThreads() {
		threads = new ArrayList<CrawlingThread>();
		List<URL> urls = Frontier.getDistinctURLFromPending();
		GetThread[] getThreadList = new GetThread[urls.size()];
		for (int i = 0; i < urls.size(); i++) {
			getThreadList[i] = new GetThread(urls.get(i));
		}
		for (int i = 0; i < urls.size(); i++) {
			getThreadList[i].start();
		}

		for (int i = 0; i < urls.size(); i++) {
			try {
				getThreadList[i].join();
			} catch (InterruptedException e) {
			}
		}
		return threads;
	}

	private static List<URL> getDistinctURLFromPending() {
		List<URL> urls = new ArrayList<URL>();

		int headerNum = Integer.parseInt(pendingUrlDB.get(HEADER));

		int i = 0;
		while (i < CrawlerConfig.MAX_THREAD_QUANTITY && !pending.isEmpty()) {
			URL e = pending.deQueue();
			headerNum++;
			while (urls.contains(e)) {
				if (pending.isEmpty())
					return urls;
				e = pending.deQueue();
				headerNum++;
			}
			urls.add(e);
			i++;
		}
		pendingUrlDB.put(HEADER, String.valueOf(headerNum));
		return urls;
	}

	// public synchronized static void saveBaiduChannelMaterials()

	public synchronized static void saveImageAs(byte[] bytes, String folder,
			String title, String url) {
		String extension = ImageLinkExtractor
				.endsWithSpecifiedExtensions(title);
		if (extension == null) {
			extension = ImageLinkExtractor.endsWithSpecifiedExtensions(url);
			title = title + "." + extension;
			if (extension == null)
				return;
		}

		int dirSerial = imageFolderCount / 100000 + 1;
		imageFolderCount++;
		String filename = IMAGE_DATA_HOME + "\\" + CrawlerConfig.PROJECT_NAME
				+ dirSerial + "\\" + folder;
		File f = new File(filename);
		if (!f.exists())
			f.mkdirs();
		File[] existedCount = f.listFiles();
		filename = f.getAbsolutePath() + "\\"
				+ String.valueOf(existedCount.length + 1) + "_"
				+ FileUtil.parseFileName(title);
		File file = new File(filename);

		try {
			FileOutputStream output = new FileOutputStream(file);
			output.write(bytes);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void writeToFileAndIndex(WebNode node) {
		// if(CrawlerConfig.CRAWLER_TYPE==1 && CrawlerConfig.CHANNEL==1){

		// return;
		// }
		int dirSerial = fileCount / 100000 + 1;
		fileCount++;
		String filename = DATA_HOME + "\\" + node.getProject() + dirSerial;
		File f = new File(filename);
		if (!f.exists())
			f.mkdirs();
		filename = f.getAbsolutePath() + "\\"
				+ FileUtil.parseFileName(node.getTitle()) + ".txt";
		File txtF = new File(filename);
		if (txtF.exists()) {
			filename = f.getAbsolutePath() + "\\"
					+ FileUtil.parseFileName(node.getTitle())
					+ String.valueOf(unrepeative) + ".txt";
			unrepeative++;
		}
		FileUtil.writeToTxt(filename, node.toString());
//		if (CrawlerConfig.createIndex())
//			index.appendToIndex(filename, node);
	}

	public synchronized static void writeOriginalHTMLToFile(WebNode node) {
		// if(CrawlerConfig.CRAWLER_TYPE==1 && CrawlerConfig.CHANNEL==1){
		// return;
		// }
		int dirSerial = originalFileCount / 100000 + 1;
		originalFileCount++;
		String filename = ORIGINAL_HTML_DATA_HOME + "\\" + node.getProject()
				+ dirSerial;
		File f = new File(filename);
		if (!f.exists())
			f.mkdirs();
		filename = f.getAbsolutePath() + "\\"
				+ FileUtil.parseFileName(node.getTitle()) + ".html";
		File txtF = new File(filename);
		if (txtF.exists()) {
			filename = f.getAbsolutePath() + "\\"
					+ FileUtil.parseFileName(node.getTitle())
					+ String.valueOf(unrepeative) + ".html";
			unrepeativeForHTML++;
		}
		FileUtil.writeToTxt(filename, node.toString());
	}

}
