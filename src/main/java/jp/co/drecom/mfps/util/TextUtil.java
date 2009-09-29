package jp.co.drecom.mfps.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

	private static final String DIGEST_MD5 = "MD5";

	public static boolean isURL(String value) {
		Pattern pattern = Pattern.compile("^(http://|https://){1}[\\w\\.\\-/:]+");
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	public static boolean isNotURL(String value) {
		return !isURL(value);
	}

	/**
	 * URLから rel-tag のタグを取得する。
	 * TODO もうちょっと賢く抽出したいかも
	 * @param value
	 * @return
	 */
	public static String extractTag(String value) {

		if(value.endsWith("/")) { // 『/』 でおわっていたら 『/』を削除
			value = value.substring(0, value.length() - 1);
		}
		String[] params = value.split("/"); // URLの最終要素を抽出
		if(params.length >= 1) {
			value = params[params.length - 1];
			params = value.split("\\?"); // パラメータも削除
			if(params.length > 1) {
				value = params[0];
			}
		}
		return value;
	}

	/**
	 * 文字列からチェックサムを生成する。
	 * チェックサムは MD5 を用いるので 128bit(32文字)固定となる
	 *
	 * @param text
	 * @return
	 */
	public static String getChecksum(String text) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(DIGEST_MD5);
		} catch (NoSuchAlgorithmException e) {
			throw new UnsupportedOperationException(e);
		}

	    messageDigest.update(text.getBytes());
	    byte[] digest = messageDigest.digest();

	    // 16進数文字列に変換
	    StringBuffer buffer = new StringBuffer();
	    for(int i=0; i< digest.length; i++){
	    	String tmp = Integer.toHexString(digest[i] & 0xff);
	    	if(tmp.length()==1){
	    		buffer.append('0').append(tmp);

	    	}else{
	    		buffer.append(tmp);
	    	}
	    }

	    return buffer.toString();
	}
}
