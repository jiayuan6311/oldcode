package config;

import util.ConfigUtil;

public class ConnectionConfig {
	public static final int TIMEOUT=ConfigUtil.getIntegerProperty("connection.timeout");
}
