package config;

import util.ConfigUtil;

public class SourceConfig {
	public static final String AUTHOR_TAG=ConfigUtil.getStringProperty("crawler.author.tag");;
	public static final String AUTHOR_ATTRIBUTE=ConfigUtil.getStringProperty("crawler.author.attribute");;
	public static final String MODIFYTIME_TAG=ConfigUtil.getStringProperty("crawler.modifyTime.tag");
	public static final String MODIFYTIME_ATTRIBUTE=ConfigUtil.getStringProperty("crawler.modifyTime.attribute");;
}
