package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import config.BDBConfig;
import config.CrawlerConfig;
import dataStructure.BDBNode;
import logger.Logger;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class BDB {

	private Environment env = null;
	private Database db = null;
	private Cursor cursor = null;


	public Database getDatabase() {
		return db;
	}

	public boolean openDatabase(String dbName) {
		try {
			EnvironmentConfig envConfig = new EnvironmentConfig();
			//envConfig.setCachePercent(70);
			envConfig.setAllowCreate(true);
			File file = new File(BDBConfig.DB_HOME);
			if (!file.exists())
				file.mkdirs();
			env = new Environment(file, envConfig);
			DatabaseConfig dbConfig = new DatabaseConfig();
			dbConfig.setAllowCreate(true);
			dbConfig.setDeferredWrite(true);
			//dbConfig.seto
			db = env.openDatabase(null, dbName, dbConfig);
		} catch (DatabaseException e) {
			System.out.println("Fail to open database");
			return false;
		}
		return true;
	}

	public boolean put(String aKey, String aValue) {
		try {
			if (aKey == null || aValue == null)
				return false;
			DatabaseEntry theKey = new DatabaseEntry(aKey
					.getBytes(CrawlerConfig.CHARSET));
			DatabaseEntry theValue = new DatabaseEntry(aValue
					.getBytes(CrawlerConfig.CHARSET));
			db.put(null, theKey, theValue);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*public BDBNode getNext(String key){
		if(!this.Contains(key))
			return null;
		String data=this.get(key);
		return new BDBNode(data, this.get(data));
	}*/
	
	public List<BDBNode> listAllData() {
		List<BDBNode> list = new ArrayList<BDBNode>();
		try {
			cursor = db.openCursor(null, null);
			DatabaseEntry foundKey = new DatabaseEntry();
			DatabaseEntry foundData = new DatabaseEntry();
			while (cursor.getNext(foundKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				try {
					String key = new String(foundKey.getData(),
							CrawlerConfig.CHARSET);
					String data = new String(foundData.getData(),
							CrawlerConfig.CHARSET);
					list.add(new BDBNode(key, data));
				} catch (UnsupportedEncodingException e) {
				}
			}
		} catch (DatabaseException e) {
		}
		cursor.close();
		return list;
	}

	public void listAllDataToFile(String fileName){
		try {
			cursor = db.openCursor(null, null);
			DatabaseEntry foundKey = new DatabaseEntry();
			DatabaseEntry foundData = new DatabaseEntry();
			while (cursor.getNext(foundKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				try {
					String key = new String(foundKey.getData(),
							CrawlerConfig.CHARSET);
					String data = new String(foundData.getData(),
							CrawlerConfig.CHARSET);
					//list.add(new BDBNode(key, data));
					
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
						bw.write(key+" "+data+"\n");
						bw.flush();
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (UnsupportedEncodingException e) {
				}
			}
		} catch (DatabaseException e) {
		}
		cursor.close();
	}
	
	public String get(String aKey) {
		String result = null;
		try {
			DatabaseEntry theKey = new DatabaseEntry(aKey
					.getBytes(CrawlerConfig.CHARSET));
			DatabaseEntry theData = new DatabaseEntry();
			if (db.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				result = new String(theData.getData(), CrawlerConfig.CHARSET);
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return result;
	}

	public boolean Contains(String aKey) {
		try {
			DatabaseEntry theKey = new DatabaseEntry(aKey
					.getBytes(CrawlerConfig.CHARSET));
			DatabaseEntry theData = new DatabaseEntry();
			if (db.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS)
				return true;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return false;
	}

	public void emptyDB() {
		List<BDBNode> nodeList = this.listAllData();
		for (BDBNode b : nodeList) {
			delete(b.getKey());
		}
	}

	public boolean delete(String aKey) {
		try {
			DatabaseEntry theKey = new DatabaseEntry(aKey
					.getBytes(CrawlerConfig.CHARSET));
			DatabaseEntry theData = new DatabaseEntry();
			if (db.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				db.delete(null, theKey);
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return false;
	}

	public boolean close() {
		try {
			if (db != null) {
				db.close();
			}
			if (env != null) {
				env.cleanLog();
				env.close();
			}
		} catch (Exception e) {
			Logger.info("Failed to close!");
		}
		return false;
	}

	public boolean sync() {
		if (db == null) {
			Logger.info("unfind the Database");
			return false;
		}
		db.sync();
		return true;

	}
}
