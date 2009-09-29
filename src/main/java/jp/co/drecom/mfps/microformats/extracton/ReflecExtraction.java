package jp.co.drecom.mfps.microformats.extracton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jp.co.drecom.mfps.log.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * リフレクションによりデータを抽出
 *
 * @author Akiko Asami
 */
public class ReflecExtraction implements Extraction {

	private Logger logger = new Logger(getClass());

	private String methodName = "";

	private String param = "";

	public ReflecExtraction(String methodName) {
		this.methodName = methodName;
		this.param = "";
	}

	public ReflecExtraction(String methodName, String param) {
		this.methodName = methodName;
		this.param = param;
	}

	public String extract(Object object) {
		String value = "";

		Method method = null;
		try {
			if(StringUtils.isBlank(param)) {
				method = object.getClass().getMethod(methodName);
				value = (String) method.invoke(object);

			} else {
				method = object.getClass().getMethod(methodName, param.getClass());
				value = (String) method.invoke(object, param);
			}

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

		}

		return value;
	}
}
