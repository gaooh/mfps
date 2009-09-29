package jp.co.drecom.mfps.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jp.co.drecom.mfps.log.Logger;

public class Crawler {

	/** User-Agent */
	//private static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)";
	private static final String USER_AGENT = "noko desu ."; // TODO –{”Ô‚Ü‚Å‚É‚Í‚©‚¦‚é

	/** logger */
	private Logger logger = new Logger(getClass());

	private String location = "";

	public Crawler(String location) {
		this.location = location;
	}

	public URLConnection accession() {
		URLConnection connection = null;
		try {
			connection = new URL(location).openConnection();

		} catch (MalformedURLException e) {
			logger.warn("this url is bad format. " + e);

		} catch (IOException e) {
			logger.warn("this url is not connection. " + e);

		} catch (Exception e) {
			logger.warn("this url is not connection. " + e);
		}
		connection.setRequestProperty("User-Agent", USER_AGENT);

		return connection;
	}

}
