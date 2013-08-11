package urlExtractor;

public class CharacterCorrecter {

	public static String correctErrorCode(String origin){
		origin=origin.replace("&amp;", "&");
		origin=origin.replace("&apos;", "'");
		origin=origin.replace("&quot;", "\"");
		return origin;
	}
	
}
