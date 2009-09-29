package jp.co.drecom.mfps.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Akiko Asami
 */
public class Message {

	protected static final Map<String, MessageFormat> formatsByPattern = new HashMap<String, MessageFormat>(32);

	/**
	 * メッセージのフォーマットを行う
	 * @param pattern
	 * @param arguments
	 * @return
	 */
	public String format(String pattern, Object ...arguments) {
		MessageFormat format = (MessageFormat) formatsByPattern.get(pattern);

		if (format == null) {
			format = new MessageFormat(pattern);
			formatsByPattern.put(pattern, format);
		}

		return format.format(arguments);
	}
}
