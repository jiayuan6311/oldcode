package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.CrawlerConfig;

public class PageUtil {

	public static void main(String[] args) {
	}

	public static String parseTagWithoutAttribute(String page, String tag,
			int fromIndex) {
		if (page == null)
			return null;
		page = PageUtil.getOriginalTagContentWithoutAttribute(page, tag,
				fromIndex);
		return PageUtil.removeBrackets(page);
	}

	public static String readFile(String filename){
		StringBuilder sb=new StringBuilder();
		String line=null;
		try {
			BufferedReader br=new BufferedReader(new FileReader(filename));
			while((line=br.readLine())!=null){
				sb.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static List<String> parseTagListWithoutAttribute(String page,
			String tag, int fromIndex) {
		if (page == null)
			return null;
		List<String> list = new ArrayList<String>();
		List<String> temp = PageUtil.getOriginalTagContentListWithoutAttribute(
				page, tag, fromIndex);
		for (String e : temp) {
			e = PageUtil.removeBrackets(e);
			list.add(e);
		}
		return list;
	}

	public static String parseTagWithAttribute(String page, String tag,
			String attribute, int fromIndex) {
		if (page == null)
			return null;
		page = PageUtil.getOriginalTagContentWithAttribute(page, tag,
				attribute, fromIndex);
		return PageUtil.removeBrackets(page);
	}

	public static List<String> parseTagListWithAttribute(String page,
			String tag, String attribute, int fromIndex) {
		if (page == null)
			return null;
		List<String> temp = PageUtil.getOriginalTagContentListWithAttribute(
				page, tag, attribute, fromIndex);
		List<String> list = new ArrayList<String>();
		for (String e : temp) {
			e = PageUtil.removeBrackets(e);
			list.add(e);
		}
		return list;
	}

	public static String getOriginalTagContentWithAttribute(String page,
			String tag, String attribute, int fromIndex) {
		if (page == null)
			return null;
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		attribute = attribute.toLowerCase();
		String leftTag = "<" + tag;
		fromIndex = lower.indexOf(leftTag, fromIndex);
		while (fromIndex >= 0) {
			int tagEnd = lower.indexOf('>', fromIndex);
			if (tagEnd < 0)
				return null;
			if (!lower.substring(fromIndex, tagEnd + 1).contains(attribute)) {
				fromIndex = lower
						.indexOf(leftTag, fromIndex + leftTag.length());
				continue;
			}
			int end = PageUtil.matchRightTag(lower, tag, fromIndex
					+ leftTag.length());
			if (end < 0)
				return null;
			return page.substring(fromIndex, end + 1);
		}
		return null;
	}

	public static List<String> getOriginalTagContentListWithAttribute(
			String page, String tag, String attribute, int fromIndex) {
		if (page == null)
			return null;
		List<String> list = new ArrayList<String>();
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		attribute = attribute.toLowerCase();
		String leftTag = "<" + tag;
		fromIndex = lower.indexOf(leftTag, fromIndex);
		while (fromIndex >= 0) {
			int tagEnd = lower.indexOf('>', fromIndex);
			if (tagEnd < 0)
				break;
			if (!lower.substring(fromIndex, tagEnd + 1).contains(attribute)) {
				fromIndex = lower
						.indexOf(leftTag, fromIndex + leftTag.length());
				continue;
			}
			int end = PageUtil.matchRightTag(lower, tag, fromIndex
					+ leftTag.length());
			if (end < 0)
				break;
			list.add(page.substring(fromIndex, end + 1));
			fromIndex = lower.indexOf(leftTag, fromIndex + leftTag.length());
		}
		return list;
	}

	public static List<String> getOriginalTagContentListWithoutAttribute(
			String page, String tag, int fromIndex) {
		if (page == null)
			return null;
		List<String> list = new ArrayList<String>();
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		String leftTag = "<" + tag;
		fromIndex = lower.indexOf(leftTag, fromIndex);
		while (fromIndex >= 0) {
			int end = PageUtil.matchRightTag(lower, tag, fromIndex
					+ leftTag.length());
			if (end < 0)
				break;
			list.add(page.substring(fromIndex, end + 1));
			fromIndex = lower.indexOf(leftTag, fromIndex + leftTag.length());
		}
		return list;
	}

	public static String getOriginalTagContentWithoutAttribute(String page,
			String tag, int fromIndex) {
		if (page == null)
			return null;
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		String leftTag = "<" + tag;
		fromIndex = lower.indexOf(leftTag, fromIndex);
		if (fromIndex < 0)
			return null;
		int end = PageUtil.matchRightTag(lower, tag, fromIndex
				+ leftTag.length());
		if (end < 0)
			return null;
		return page.substring(fromIndex, end + 1);
	}

	public static String correctErrerCode(String text) {
		text = PageUtil.replacePiece(text, "&#91;", "[");
		text = PageUtil.replacePiece(text, "&#93;", "]");
		text = PageUtil.replacePiece(text, "&nbsp;", "");
		text = PageUtil.replacePiece(text, "&quot;", "\"");
		text = PageUtil.replacePiece(text, "&#12539;", "¡¤");
		text = PageUtil.replacePiece(text, "&gt;", ">");
		text = PageUtil.replacePiece(text, "&#160;", " ");
		text = PageUtil.replacePiece(text, "&#8206;", " ");
		text = PageUtil.replacePiece(text, "&middot;", "¡¤");
		text = PageUtil.replacePiece(text, "&#39;", "'");
		return text;
	}

	public static String removeRedundantReturn(String page) {
		if (page == null)
			return page;
		if (page == null || page.length() == 1)
			return page;
		int index = page.indexOf("\n\n");
		while (index >= 0) {
			if (index == 0)
				page = page.substring(index + 2, page.length());
			else
				page = page.substring(0, index)
						+ page.substring(index + 1, page.length());
			index = page.indexOf("\n\n");
		}

		if (page.length() < 2)
			return page;
		while (page.charAt(0) == '\n')
			page = page.substring(1, page.length());
		return page;
	}

	public static String deleteTagWithoutAttribute(String page, String tag) {
		if (page == null)
			return null;
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		String leftTag = "<" + tag + ">";
		//String rightTag = "</" + tag + ">";
		int index=lower.indexOf(leftTag);
		//int start = lower.indexOf(leftTag);
		while (index >= 0) {
			//int index = start + leftTag.length();
			int end = PageUtil.matchRightTag(lower, tag, index+leftTag.length());
			if (end == -1)
				return page;
			// end+=rightTag.length();
			page = page.substring(0, index)
					+ page.substring(end + 1, page.length());
			lower = page.toLowerCase();
			index = lower.indexOf(leftTag);
		}
		return page;
	}

	public static String deleteSentenceWithKey(String page, String key){
		String[] stops={"¡£", "£¡", "£¿", "¡­¡­", "\n\n"};
		int index=page.indexOf(key);
		if(index<0)return page;
		page=page.substring(0, index);
		int max=-1;
		for(int i=0 ;i <stops.length; i++){
			index=page.lastIndexOf(stops[i]);
			if(index>max)
				max=index;
		}
		if(max>0)page=page.substring(0, max+1);
		return page;
	}
	
	public static String deleteTagWithAttribute(String page, String tag,
			String attribute) {
		if (page == null)
			return page;
		String lower = page.toLowerCase();
		attribute = attribute.toLowerCase();
		tag = tag.toLowerCase();
		String leftTag = "<" + tag;
		//String rightTag = "</" + tag + ">";
		int index=lower.indexOf(leftTag);
		while(index>=0){
			int tagEnd=lower.indexOf('>', index);
			if(tagEnd<0)
				return page;
			if(!lower.substring(index, tagEnd+1).contains(attribute)){
				index=lower.indexOf(leftTag, index+leftTag.length());
				continue;
			}
			int end=PageUtil.matchRightTag(lower, tag, index+leftTag.length());
			if(end<0)
				break;
			page=page.substring(0, index)+page.substring(end+1, page.length());
			lower=page.toLowerCase();
			index=lower.indexOf(leftTag);
		}
		return page;
		/*int start = lower.indexOf(leftTag);
		int index = start;
		while (start > 0 && index < lower.length()) {
			if (lower.charAt(index) == '>') {
				if (!lower.substring(start, ++index).contains(attribute)) {
					start = lower.indexOf(leftTag, index);
					index = start;
				} else {
					int end = PageUtil.matchRightTagWithAttribute(lower, tag,
							index);
					if (end == -1)
						return page;
					end += rightTag.length();
					if (end == page.length())
						return page.substring(0, start);
					page = page.substring(0, start)
							+ page.substring(end + 1, page.length());
					lower = page.toLowerCase();
					start = lower.indexOf(leftTag);
					index = start;
				}
			}
			index++;
		}*/
		
	}

	/*
	 * public static int matchRightTagWithoutAttribute(String page, String tag,
	 * int fromIndex) { if (page == null) return -1; String rightTag = "</" +
	 * tag + ">"; String leftTag = "<" + tag + ">"; int index = fromIndex; int
	 * match = 1; while (index < page.length()) { if ((index + leftTag.length())
	 * < page.length() && page.substring(index, index +
	 * leftTag.length()).equals( leftTag)) { match++; index += leftTag.length();
	 * index--; continue; } if ((index + rightTag.length()) <= page.length() &&
	 * page.substring(index, index + rightTag.length()).equals( rightTag))
	 * match--; if (match == 0) return index - 1; index++; } return -1; }
	 */

	public static int matchRightTag(String page, String tag, int fromIndex) {
		if (page == null)
			return -1;
		String leftTag = "<" + tag;
		String rightTag = "</" + tag + ">";
		int match = 1;
		int index = fromIndex;
		while (index < page.length()) {
			if (index <= (page.length() - leftTag.length())
					&& page.substring(index, index + leftTag.length()).equals(
							leftTag)) {
				index += leftTag.length();
				match++;
				// if(match==0)
				// return index-1;
				index--;
				continue;
			}
			if (index <= (page.length() - rightTag.length())
					&& page.substring(index, index + rightTag.length()).equals(
							rightTag)) {
				index += rightTag.length();
				match--;
				if (match == 0)
					return index - 1;
				index--;
				continue;
			}

			index++;
		}
		return -1;
	}

	public static int matchRightTagWithAttribute(String page, String tag,
			int fromIndex) {
		if (page == null)
			return -1;
		String rightTag = "</" + tag + ">";
		String leftTag = "<" + tag;
		int index = fromIndex;
		int match = 1;
		while (index < page.length()) {
			if ((index + leftTag.length()) < page.length()
					&& page.substring(index, index + leftTag.length()).equals(
							leftTag)) {
				match++;
				index += leftTag.length();
				index--;
				continue;
			}
			if ((index + rightTag.length()) <= page.length()
					&& page.substring(index, index + rightTag.length()).equals(
							rightTag))
				match--;
			if (match == 0)
				return index - 1;
			index++;
		}
		return -1;
	}

	public static String removeBrackets(String page) {
		if (page == null)
			return page;
		int i = 0, start = 0, end = 0;
		while (i < page.length()) {
			if (page.charAt(i) == '<') {
				start = i;
				while (i < page.length()) {
					if (page.charAt(i) == '>') {
						end = i + 1;
						break;
					}
					i++;
				}
				if (end > page.length())
					break;
				page = PageUtil.deletePiece(page, page.substring(start, end));
				i = 0;
				continue;
			}
			i++;
		}
		return page;
	}

	public static String deletePiece(String page, String piece) {
		if (page == null)
			return page;
		int position = 0;
		while (position >= 0) {
			position = page.indexOf(piece);
			if (position < 0)
				return page;
			page = page.substring(0, position)
					+ page.substring(position + piece.length(), page.length());
		}
		return page;
	}

	public static String replacePiece(String page, String origin,
			String modified) {
		if (page == null)
			return page;
		int position = 0;
		while (position >= 0) {
			position = page.indexOf(origin);
			if (position < 0)
				return page;
			page = page.substring(0, position) + modified
					+ page.substring(position + origin.length(), page.length());
		}
		return page;
	}

	public static String getEncoding(String page) {
		int index = page.indexOf("charset=");
		if (index < 0)
			return CrawlerConfig.CHARSET;
		index += 8;
		int tail = index;
		while (tail < page.length()) {
			if (page.charAt(tail) == '"')
				break;
			tail++;
		}
		String charset = page.substring(index, tail).toUpperCase();
		if (charset.equals("GBK"))
			return "GBK";
		if (charset.equals("GB2312"))
			return "GB2312";
		if (charset.equals("UTF-8"))
			return "UTF-8";
		return CrawlerConfig.CHARSET;
	}

	public static String deleteReturnAndTab(String origin) {
		if (origin.length() < 2)
			return origin;
		int index = origin.indexOf('\r');
		while (index >= 0) {
			if (index == 0)
				origin = origin.substring(1, origin.length());
			else
				origin = origin.substring(0, index)
						+ origin.substring(index + 1, origin.length());
			index = origin.indexOf('\r');
		}
		index = origin.indexOf('\t');
		while (index >= 0) {
			if (index == 0)
				origin = origin.substring(1, origin.length());
			else
				origin = origin.substring(0, index)
						+ origin.substring(index + 1, origin.length());
			index = origin.indexOf('\t');
		}
		return origin;
	}

	public static String string2keyword(String str, String charset) {
		String isn = ByteUtil.string2ISN(str, charset);
		String keyword = "";
		for (int i = 0; i < isn.length(); i += 2) {
			keyword += "%";
			keyword += isn.substring(i, i + 2);
		}
		return keyword;
	}

	public static String getTitleFromAbsoluteFileName(String fileName){
		int index=fileName.lastIndexOf('/');
		if(index<0)
			index=fileName.lastIndexOf('\\');
		if(index<0)
			return fileName;
		return fileName.substring(index+1, fileName.length());
	}
	
	public static String extractTitle(String origin) {
		if (origin == null)
			return null;
		String lower = origin.toLowerCase();
		int front = lower.indexOf("<title>") + 7;
		int tail = lower.indexOf("</title>");
		if (tail < origin.length() && front < tail)
			return origin.substring(front, tail).trim();
		else
			return null;
	}

	public static List<String> extractAllValuesWithAttribute(String page,
			String tag, String attribute) {
		List<String> list = new ArrayList<String>();
		String lower = page.toLowerCase();
		tag = tag.toLowerCase();
		attribute = attribute.toLowerCase();
		String leftTag = "<" + tag + " ";
		String rightTag = "</" + tag + ">";
		int index = 0;
		while (index >= 0) {
			index = lower.indexOf(leftTag, index);
			if (index < 0)
				break;
			// int start=index;
			int attrIndex = lower.indexOf(attribute, index);
			index = lower.indexOf(rightTag, index);
			if (index < 0)
				break;
			// int end=index;
			if (attrIndex < 0 || attrIndex > index)
				continue;
			int leftQuote = page.indexOf("\"", attrIndex);
			if (leftQuote < 0 || leftQuote > index)
			{
				leftQuote=page.indexOf("'", attrIndex);
				if(leftQuote < 0 || leftQuote > index)
				continue;
			}
				
			int rightQuote = page.indexOf("\"", leftQuote + 1);
			if (rightQuote < 0 || rightQuote > index){
				rightQuote=page.indexOf("'", attrIndex);
				if(rightQuote < 0 || rightQuote > index)
				continue;
			}
			String value=null;
			if(leftQuote+1<rightQuote)
			value = page.substring(leftQuote + 1, rightQuote);
			if (value!=null && !list.contains(value))
				list.add(page.substring(leftQuote + 1, rightQuote));
		}
		return list;
	}
}
