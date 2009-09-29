package jp.co.drecom.mfps.microformats.extracton;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.util.TextUtil;

/**
 * RelTagデータを抽出
 *
 * @author Akiko Asami
 */
public class RelTagExtraction implements Extraction {

	private static final String DECODE_CHARACTER = "UTF-8";

	private Logger logger = new Logger(getClass());

	/* (非 Javadoc)
	 * @see jp.co.drecom.mfps.microformats.extracton.Extraction#extract(java.lang.Object)
	 */
	public String extract(Object object) {
		String value = "";

		Method method = null;
		try {
			method = object.getClass().getMethod("getLink");
			value = (String) method.invoke(object);

			value = TextUtil.extractTag(value);
			value = URLDecoder.decode(value, DECODE_CHARACTER);

		} catch (SecurityException e) {
			logger.warn(e);

		} catch (NoSuchMethodException e) {
			logger.warn(e);

		} catch (IllegalArgumentException e) {
			logger.warn(e);

		} catch (IllegalAccessException e) {
			logger.warn(e);

		} catch (InvocationTargetException e) {
			logger.warn(e);

		} catch (UnsupportedEncodingException e) {
			logger.warn(e);
		}

		return value;
	}

}
