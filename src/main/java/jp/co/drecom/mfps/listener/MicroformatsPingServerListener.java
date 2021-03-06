package jp.co.drecom.mfps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.co.drecom.mfps.common.ApplicationContextFactory;
import jp.co.drecom.mfps.controller.Channel;

public class MicroformatsPingServerListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		/* -- Spring 初期化 --*/
		ApplicationContextFactory.getApplicationContext(servletContextEvent.getServletContext());

		/* -- Parser Worker Thread 初期化 -- */
		Channel channel = Channel.getInstance();
		channel.startWorkers();
	}

	public void contextDestroyed(ServletContextEvent servletContext) {
		// TODO 終了時
	}

}
