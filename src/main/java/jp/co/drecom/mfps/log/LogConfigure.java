package jp.co.drecom.mfps.log;

/**
 * ÉçÉOê›íËÉNÉâÉX
 * @author Akiko Asami
 */
public class LogConfigure {

	private static LogConfigure instance = new LogConfigure();

	private String propFileName = "";

	private int watchTime = 0;

	private LogConfigure() {
		propFileName = "log4j.xml";
		watchTime = 60000;
	}

	public static LogConfigure getInstance() {
		if (instance == null) {
			instance = new LogConfigure();
		}
		return instance;
	}

	public String getPropFileName() {
		return propFileName;
	}

	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}

	public int getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(int watchTime) {
		this.watchTime = watchTime;
	}

}
