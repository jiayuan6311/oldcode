package textExtractor;

import dataStructure.WebNode;
import util.PageUtil;
import util.Time;

public class SinaNewsTextExtractor implements TextExtractor{

	
	@Override
	public WebNode extractText(WebNode origin) {
		String originText=origin.getText();
		originText=PageUtil.deleteReturnAndTab(originText);
		String title=PageUtil.extractTitle(originText);
		int index=title.indexOf("_��������");
		if(index>0){
			title=title.substring(0, index);
		}
		String author = PageUtil.parseTagWithAttribute(originText, "a", "citiao_right_jibenxinxi_chuangjianzhe", 0);
		
		originText=PageUtil.deleteTagWithoutAttribute(originText, "head");
		originText=PageUtil.deleteTagWithAttribute(originText, "div", "nav-top");
		originText=PageUtil.deleteTagWithAttribute(originText, "div", "thslinkwz");
		originText=PageUtil.deleteTagWithAttribute(originText, "span", "\"gray9 l score");
		originText=PageUtil.deleteTagWithAttribute(originText, "div", "docfavhref");
		originText=PageUtil.deleteTagWithAttribute(originText, "div", "\"catalog\"");
		originText=PageUtil.deleteTagWithAttribute(originText, "span", "DISPLAY");
		originText=PageUtil.deleteTagWithAttribute(originText, "span", "nav-module");
		String text=PageUtil.parseTagWithAttribute(originText, "div", "\"content\"", 0);
		text=PageUtil.deleteSentenceWithKey(text, "�ο�����");
		text=PageUtil.deletePiece(text, "����༭����");
		text=PageUtil.replacePiece(text, "����༭ժҪ", "\n");
		text=PageUtil.correctErrerCode(text);
		text=PageUtil.removeRedundantReturn(text);
		index=text.indexOf("\n");
		while(index>=0){
			text=text.substring(0, index+1)+"  "+text.substring(index+1, text.length());
			index=text.indexOf("\n",index+3);
		}
		WebNode wn=new WebNode(origin.getUrl(), title, text);
		if(author!=null)
			wn.setAuthor(author);
		else
			wn.setAuthor("��������");
		wn.setTime(Time.getStringCurrentTime());
		wn.setComeFrom("��������");
		return wn;
	}
}
