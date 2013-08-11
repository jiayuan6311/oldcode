package textExtractor;

import config.CrawlerConfig;
import config.SourceConfig;
import dataStructure.WebNode;
import util.PageUtil;
import util.Time;

public class CustomizedTextExtractor implements TextExtractor{

	
	@Override
	public WebNode extractText(WebNode origin) {
		String text=origin.getText();
		String title=PageUtil.extractTitle(text);
		String authorTag=SourceConfig.AUTHOR_TAG.trim();
		String authorAttribute=SourceConfig.AUTHOR_ATTRIBUTE.trim();
		String modifyTimeTag=SourceConfig.MODIFYTIME_TAG.trim();
		String modifyTimeAttribute=SourceConfig.MODIFYTIME_ATTRIBUTE.trim();
		
		String author=null;
		String time=null;
		
		if(!authorTag.equals("") && !authorAttribute.equals(""))
			author=PageUtil.parseTagWithAttribute(text, authorTag, authorAttribute, 0);
		if(!modifyTimeTag.equals("") && !modifyTimeAttribute.equals(""))
			time=PageUtil.parseTagWithAttribute(text, modifyTimeTag, modifyTimeAttribute, 0);
		
		text=PageUtil.deleteTagWithoutAttribute(text, "style");
		text=PageUtil.deleteTagWithoutAttribute(text, "script");
		text=PageUtil.deleteTagWithAttribute(text, "style", " ");
		text=PageUtil.deleteTagWithAttribute(text, "script", " ");
		text=PageUtil.deleteReturnAndTab(text);
		text=PageUtil.removeBrackets(text);
		text=PageUtil.deletePiece(text, "      ");
		text=PageUtil.deletePiece(text, "     ");
		text=PageUtil.deletePiece(text, "    ");
		text=PageUtil.deletePiece(text, "   ");
		text=PageUtil.deletePiece(text, "  \n  ");
		text=PageUtil.removeRedundantReturn(text);
		text=PageUtil.correctErrerCode(text);
		WebNode wn=new WebNode(CrawlerConfig.PROJECT_NAME, origin.getUrl(), title, text);
		
		if(author!=null)
			wn.setAuthor(author);
		else
			wn.setAuthor(CrawlerConfig.CRAWLER_COMEFROM);
		if(time!=null)
			wn.setTime(time);
		else
			wn.setTime(Time.getStringCurrentTime());
		
		wn.setComeFrom(CrawlerConfig.CRAWLER_COMEFROM);
		return wn; 
	}

}
