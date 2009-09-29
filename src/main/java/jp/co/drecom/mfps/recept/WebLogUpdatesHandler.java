package jp.co.drecom.mfps.recept;

import java.util.Hashtable;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.config.MessageConfiguration;
import jp.co.drecom.mfps.controller.polling.PollingManager;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;
import jp.co.drecom.mfps.util.TimestampUtil;
import jp.co.drecom.mfps.util.TextUtil;

import org.apache.commons.lang.StringUtils;

/**
 * webLogUpdates ���󂯎�����Ƃ���Handler
 *
 * @author Akiko Asami
 */
public class WebLogUpdatesHandler {

	/** ���X�|���X:���b�Z�[�W*/
	private static final String MESSGE = "message";

	/** ���X�|���X:���ʃR�[�h*/
	private static final String RESULT_CODE = "error";

	private MessageConfiguration messageConfig = MessageConfiguration.getInstance();

	private Logger logger = new Logger(getClass());

	private String ip = null;

	public WebLogUpdatesHandler(String ip) {
		this.ip = ip;
	}

	public Hashtable ping(String name, String url) {
		Hashtable<String, Object> result = new Hashtable<String, Object>();

		String errorCode = validName(name);
		String message = "recept ping. Thanks";

		if(StringUtils.isNotBlank(errorCode)) { // name �����̃`�F�b�N
			result.put(MESSGE, errorCode);
			result.put(RESULT_CODE, Boolean.TRUE);
			return result;
		}

		errorCode = validURL(url);

		if(StringUtils.isNotBlank(errorCode)) { // url �����̃`�F�b�N
			result.put(MESSGE, errorCode);
			result.put(RESULT_CODE, Boolean.TRUE);
			return result;
		}

		try { // TODO �܂Ƃ߂đ}��

			logger.info("recept :" + name + "," + url + "," + ip);

			WebLogUpdateDao dao = DaoFactory.getWebLogUpdateDao();

			WebLogUpdateDto weblogupdate = new WebLogUpdateDto();
			weblogupdate.setName(name);
			weblogupdate.setUrl(url);
			weblogupdate.setIp(ip);
			weblogupdate.setRegistDate(TimestampUtil.getNowForSQL());
			dao.insert(weblogupdate);

			PollingManager manager = PollingManager.getInstance();
			manager.knock(); // �����Ώےǉ����m�b�N

		} catch (Exception e) {
			logger.error(e);
			errorCode = "error.weblogUpdates.server.fail";
			result.put(RESULT_CODE, Boolean.TRUE);
			return result;
		}

		result.put(MESSGE, message);
		result.put(RESULT_CODE, Boolean.FALSE);

		return result;
	}

	/**
	 * Name �p�����[�^�̐��������`�F�b�N���A��肪����΃G���[���b�Z�[�W��Ԃ��B
	 * ��肪�Ȃ���Β���0�̕������Ԃ��B
	 * @param name
	 * @return
	 */
	protected String validName(String name) {
		if(name == null || name.length() == 0) {
			return messageConfig.getString("error.weblogUpdates.name.required");
		}
		if(name.length() > 1024) {
			return messageConfig.getString("error.weblogUpdates.name.longger", 1024);
		}
		return "";
	}

	/**
	 * Name �p�����[�^�̐��������`�F�b�N���A��肪����΃G���[���b�Z�[�W�̃L�[��Ԃ��B
	 * ��肪�Ȃ���Β���0�̕������Ԃ��B
	 * @param name
	 * @return
	 */
	protected String validURL(String url) {
		if(url == null || url.length() == 0) {
			return messageConfig.getString("error.weblogUpdates.name.required");
		}
		if(url.length() > 256) {
			return messageConfig.getString("error.weblogUpdates.url.longger", 256);
		}
		if(TextUtil.isNotURL(url)) {
			return messageConfig.getString("error.weblogUpdates.url.format", url);
		}

		return "";
	}


}
