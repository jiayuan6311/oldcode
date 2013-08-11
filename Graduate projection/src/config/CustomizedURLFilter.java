package config;

import java.util.List;

import util.ConfigUtil;

public class CustomizedURLFilter {
	public static final List<String> REJECTED_PATTERNS=ConfigUtil.getListProperity("crawler.rejected_patterns");
	public static final List<String> DOWNLOAD_PATTERNS=ConfigUtil.getListProperity("crawler.download_patterns");
	public static final List<String> ACCEPTED_PATTERNS=ConfigUtil.getListProperity("crawler.accepted_patterns");
	public static final String HOST_ADD=ConfigUtil.getStringProperty("crawler.host");
}
