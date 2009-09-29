package jp.co.drecom.mfps.config;

import java.util.ResourceBundle;

import jp.co.drecom.mfps.common.Message;


/**
 * メッセージ設定クラス
 *
 * @author Akiko Asami
 */
public class MessageConfiguration {

	private static final String PROPERTIES_FILE_PATH = "message_resource";

	private static MessageConfiguration instance = null;

	private ResourceBundle config = null;

	private Message message = null;

	private MessageConfiguration() {
		config = ResourceBundle.getBundle(PROPERTIES_FILE_PATH);
		message = new Message();
	}

	public static MessageConfiguration getInstance() {
		if(instance == null) {
			instance = new MessageConfiguration();
		}
		return instance;
	}

	public String getString(String key) {
		return config.getString(key);
	}

	public String getString(String key, Object ...obj) {
		String pattern = getString(key);
		return message.format(pattern, obj);
	}
}
