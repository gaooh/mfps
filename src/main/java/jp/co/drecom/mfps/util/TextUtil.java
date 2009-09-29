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
	 * URL���� rel-tag �̃^�O���擾����B
	 * TODO ����������ƌ������o����������
	 * @param value
	 * @return
	 */
	public static String extractTag(String value) {

		if(value.endsWith("/")) { // �w/�x �ł�����Ă����� �w/�x���폜
			value = value.substring(0, value.length() - 1);
		}
		String[] params = value.split("/"); // URL�̍ŏI�v�f�𒊏o
		if(params.length >= 1) {
			value = params[params.length - 1];
			params = value.split("\\?"); // �p�����[�^���폜
			if(params.length > 1) {
				value = params[0];
			}
		}
		return value;
	}

	/**
	 * �����񂩂�`�F�b�N�T���𐶐�����B
	 * �`�F�b�N�T���� MD5 ��p����̂� 128bit(32����)�Œ�ƂȂ�
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

	    // 16�i��������ɕϊ�
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
