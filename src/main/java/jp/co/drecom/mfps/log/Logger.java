package jp.co.drecom.mfps.log;

import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * MPFS�p���K�[ log4j ���Ϗ����ė��p����B
 *
 * @author Akiko Asami
 */
public class Logger {

	static {
		LogConfigure configer = LogConfigure.getInstance();
		URL url = jp.co.drecom.mfps.log.Logger.class.getResource("/" + configer.getPropFileName());
		DOMConfigurator.configureAndWatch(url.getPath(), configer.getWatchTime());
	}

	private String classname = "";

	private org.apache.log4j.Logger logger = null;

	public Logger(Class classObj) {
		classname = classObj.getName();
		logger = org.apache.log4j.Logger.getLogger(classname);
	}

	/**
	 * Debug�p �J�����̂ݕK�v�ȏ��
	 * @param message
	 */
	public void debug(String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(format(message));
		}
	}

	/**
	 * ���폈�������L�^�Ɏc���Ă��������ꍇ
	 * @param message
	 */
	public void info(String message) {
		if(logger.isInfoEnabled()) {
			logger.info(format(message));
		}
	}

	/**
	 * �����Ƃ��Ăُ͈킾���A�z��͈͊O�ł���A��O�ł͂Ȃ��Ƃ�
	 * @param message
	 */
	public void warn(String message) {
		logger.warn(format(message));
	}

	/**
	 * �T�[�r�X�p���ɂ͖�肪�Ȃ�����O�������������Ƃ�
	 * @param message
	 */
	public void error(String message) {
		logger.error(format(message));
	}

	/**
	 * �T�[�r�X�p���ɒv���I�ȏꍇ
	 * @param message
	 */
	public void fatal(String message) {
		logger.fatal(format(message));
	}

	public void debug(Throwable ex) {
		logger.debug(classname, ex);
	}

	public void info(Throwable ex) {
		logger.info(classname, ex);
	}

	public void error(Throwable ex) {
		logger.error(classname, ex);
	}

	public void fatal(Throwable ex) {
		logger.fatal(classname, ex);
	}

	public void warn(Throwable ex) {
		logger.warn(classname, ex);
	}

	protected String format(String message) {
		return classname + " " + message;
	}
}
