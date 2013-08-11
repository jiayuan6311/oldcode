package config;

import util.ConfigUtil;

public class BDBConfig {
	public static final String DB_HOME=ConfigUtil.getStringProperty("database.home");
	public static final long DB_FLUSH_INTERVAL=ConfigUtil.getIntegerProperty("database.flush_interval")*60000;
}
