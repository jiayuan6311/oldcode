package textExtractor;

import config.CrawlerConfig;
import dataStructure.WebNode;
import util.FileUtil;
import util.PageUtil;
import util.Time;

public class BaiduBaikeTextExtractor implements TextExtractor {

	public static void main(String[] args) {
		String page = FileUtil.getText("D:\\a.html");
		WebNode wn = new WebNode("test", "test", page);
		BaiduBaikeTextExtractor bte = new BaiduBaikeTextExtractor();
		wn = bte.extractText(wn);
		System.out.println(wn.toString());
	}

	public WebNode extractText(WebNode origin) {
		String originText = origin.getText();
		if (originText.contains("ͬ���") && originText.contains("����������"))
			return null;
		if (originText.contains("searchRedirect"))
			return null;
		int index = originText.indexOf('\r');
		while (index >= 0) {
			if (index == 0 && originText.length() > 1) {
				originText = originText.substring(1, originText.length());
				index = originText.indexOf('\r');
			} else {
				originText = originText.substring(0, index)
						+ originText.substring(index + 1, originText.length());
				index = originText.indexOf('\r');
			}
		}
		String time = PageUtil.parseTagWithAttribute(originText, "span",
				"lastModifyTime", 0);
		String author = PageUtil.parseTagWithAttribute(originText, "span",
				"nslog:1022", 0);
		originText = PageUtil.deleteTagWithAttribute(originText, "style",
				"type=");
		originText = PageUtil.deleteTagWithAttribute(originText, "script",
				"text");
		originText = PageUtil.deleteTagWithAttribute(originText, "div",
				"tieba-gallery-box");
		String lemmaContent = PageUtil.parseTagWithAttribute(originText, "div",
				"lemmaContent", 0);
		String cardSummary = PageUtil.parseTagWithAttribute(originText, "div",
				"cardSummary", 0);
		String cardInfo = PageUtil.parseTagWithAttribute(originText, "div",
				"card-info", 0);
		lemmaContent = PageUtil.removeRedundantReturn(lemmaContent);
		cardSummary = PageUtil.removeRedundantReturn(cardSummary);
		cardInfo = PageUtil.removeRedundantReturn(cardInfo);
		String page = "";
		if (cardSummary != null) {
			page += "�ٶ���Ƭ\n" + cardSummary;
		}
		if (cardInfo != null)
			page += cardInfo;
		if (page.equals(""))
			page += "\n";
		page += "��ϸ����\n" + lemmaContent;
		String title = PageUtil.extractTitle(origin.getText());
		index = title.indexOf("_�ٶȰٿ�");
		title = title.substring(0, index);
		
		//END: �ٶȰٿ�Ƶ�����⴦��
		WebNode wn = new WebNode(origin.getUrl(), title, page);
		String text = wn.getText();
		if (text == null) {
			text = origin.getText();
		}
		text = PageUtil.deletePiece(text, "[�༭����]");
		text=PageUtil.deletePiece(text, "��껬���û�������������Ƭ����");
		text = PageUtil.correctErrerCode(text);
		if (author != null)
			wn.setAuthor(author);
		else
			wn.setAuthor("�ٶȰٿ�");
		if (time != null)
			wn.setTime(time);
		else
			wn.setTime(Time.getStringCurrentTime());
		wn.setText(text);
		wn.setComeFrom("�ٶȰٿ�");
		return wn;
	}

}
