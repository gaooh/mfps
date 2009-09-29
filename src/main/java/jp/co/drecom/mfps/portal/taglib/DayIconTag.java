package jp.co.drecom.mfps.portal.taglib;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.util.DateUtil;

import org.apache.commons.lang.StringUtils;

public class DayIconTag extends TagSupport {

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
		int day = -1;
		Date dtDate = null;

		logger.debug("dtstart:" + dtstart);
		if(StringUtils.isNotBlank(dtstart)) {
			dtDate = DateUtil.getParseDate(dtstart, "yyyy-MM-dd HH:mm:ss.S");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(dtDate.getTime());

			day = calendar.get(Calendar.DAY_OF_MONTH);
		}

		try {
			pageContext.getOut().print(day);
		} catch (IOException e) {
			logger.error(e);
		}

		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}
}
