package jp.co.drecom.mfps.log;

import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * MPFS用ロガー log4j を委譲して利用する。
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
	 * Debug用 開発時のみ必要な情報
	 * @param message
	 */
	public void debug(String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(format(message));
		}
	}

	/**
	 * 正常処理だか記録に残しておきたい場合
	 * @param message
	 */
	public void info(String message) {
		if(logger.isInfoEnabled()) {
			logger.info(format(message));
		}
	}

	/**
	 * 処理としては異常だか、想定範囲外であり、例外ではないとき
	 * @param message
	 */
	public void warn(String message) {
		logger.warn(format(message));
	}

	/**
	 * サービス継続には問題がないが例外等がおこったとき
	 * @param message
	 */
	public void error(String message) {
		logger.error(format(message));
	}

	/**
	 * サービス継続に致命的な場合
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
