package jp.co.drecom.mfps.portal.taglib;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.util.DateUtil;

import org.apache.commons.lang.StringUtils;

/**
 * @author Akiko Asami
 *
 */
public class TimeIconTag extends TagSupport  {

	private Logger logger = new Logger(getClass());

	/** ŠJŽn“ú•t */
	private String dtstart;

	/**
	 * @param key
	 */
	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
	}

	/* (”ñ Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String timeIcon = "no";
		Date dtDate = null;

		logger.debug("dtstart:" + dtstart);
		if(StringUtils.isNotBlank(dtstart)) {
			dtDate = DateUtil.getParseDate(dtstart, "yyyy-MM-dd HH:mm:ss.S");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(dtDate.getTime());

			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if(hour > 0 && hour <= 6) {
				timeIcon = "01";
			} else if ( hour > 6  && hour <= 11) {
				timeIcon = "02";
			} else if ( hour > 11 && hour <= 16) {
				timeIcon = "03";
			} else if ( hour > 16 && hour <= 20) {
				timeIcon = "04";
			} else if ( hour > 20 && hour <= 24) {
				timeIcon = "05";
			}
		}

		try {
			pageContext.getOut().print(timeIcon);
		} catch (IOException e) {
			logger.error(e);
		}

		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

}
