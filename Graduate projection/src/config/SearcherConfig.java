package config;


import java.util.List;

import util.ConfigUtil;

public class SearcherConfig {
	public static final String INDEX_PATH=ConfigUtil.getStringProperty("searcher.index_path");
	public static final String SEARCH_OUTPUT_DIR=ConfigUtil.getStringProperty("searcher.output_dir");
	public static final int TOP_DOCS=ConfigUtil.getIntegerProperty("searcher.top_docs");
	
	public static final List<String> RESTRICT_LIST=ConfigUtil.getListProperity("searcher.project_restriction");
	public static final String PROJECT_RESTRIC=ConfigUtil.getStringProperty("searcher.project_restriction");
	
	public static final int FLUSH_PAGES=ConfigUtil.getIntegerProperty("searcher.flush_pages");
	
	public static final boolean isRemovable(){
		if(ConfigUtil.getIntegerProperty("searcher.removable")==1)
			return true;
		return false;
	}
}
