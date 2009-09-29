package jp.co.drecom.mfps.common;

import javax.servlet.ServletContext;

import jp.co.drecom.mfps.log.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * SpringíÜêï
 *
 * @author Akiko Asami
 */
public class ApplicationContextFactory {

	private static Logger logger = new Logger(ApplicationContextFactory.class);

	private static ApplicationContext ctx = null;

	private static final String DEFALUT_CONTEXT = "applicationContext.xml";

	private ApplicationContextFactory() {
		// Sigleton
	}

	public static ApplicationContext getApplicationContext(Object initObj) {
		if (ctx == null) {
			if (initObj == null) {
				String msg = "Application context not initialized";
				logger.fatal(msg);
				throw new IllegalStateException(msg);

			} else if (initObj instanceof String) {
				logger.info("Application context initialize of ClassPathXmlApplicationContext");
				ctx = new ClassPathXmlApplicationContext((String) initObj);

			} else if (initObj instanceof ServletContext) {
				logger.info("Application context initialize of WebApplicationContextUtils");
				ctx = WebApplicationContextUtils.getWebApplicationContext((ServletContext) initObj);

			} else {
				String msg = "You must initialize the context String or ServletContext";
				logger.fatal(msg);
				throw new IllegalStateException(msg);
			}
		}

		return ctx;
	}

	public static Object getContainer(String beanName) {

		ctx = getApplicationContext(DEFALUT_CONTEXT);
		return ctx.getBean(beanName);
	}

}
