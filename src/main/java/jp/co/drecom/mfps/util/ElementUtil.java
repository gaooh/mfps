package jp.co.drecom.mfps.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Element;

public class ElementUtil {

	private static Logger logger = new Logger(ElementUtil.class);

	/**
	 * HashMapì‡ÇÃ jp.co.drecom.mfps.microformats.Element óvëf Ç object Ç…ê›íËÅB
	 * @param elements
	 */
	public static void copyElements(HashMap elements, Object object) {

		if(object == null) {
			return ;
		}

		for(Iterator itrElements = elements.keySet().iterator(); itrElements.hasNext(); ) {
			String key = (String) itrElements.next();
			Element element = (Element)elements.get(key);

			try {
				key = key.toLowerCase();
				String[] keys = key.split("_");

				String setterMethod = "set";
				for(int i = 0; i < keys.length; i++) {
					setterMethod += keys[i].substring(0, 1).toUpperCase() + keys[i].substring(1);
				}

				if(element.getValue() != null) {
					Method method = object.getClass().getMethod(setterMethod, element.getValue().getClass());
					method.invoke(object, element.getValue());
				}

			} catch (IllegalArgumentException e) {
				logger.warn(e);

			} catch (IllegalAccessException e) {
				logger.warn(e);

			} catch (SecurityException e) {
				logger.warn(e);

			} catch (NoSuchMethodException e) {
				logger.warn(e);

			} catch (InvocationTargetException e) {
				logger.warn(e);
			}

		}
	}
}
