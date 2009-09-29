package jp.co.drecom.mfps.portal.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.model.module.HCalendarModule;
import jp.co.drecom.mfps.portal.api.param.HCalendarParameter;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;

public class HCalendarAPIServlet extends VelocityServlet {

	private Logger logger = new Logger(getClass());

	private static final String VERSION = "1.0";

	/* (非 Javadoc)
	 * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
	 */
	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
			throws Exception {

		response.setContentType("text/xml;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Template template = null;
		try {
			HCalendarParameter paramter = new HCalendarParameter(request);

			HCalendarModule hCalendarModule = new HCalendarModule();
			List hCalendars = hCalendarModule.findByCondition(paramter);

			template = getTemplate("/apivm/hCalendar.vm");
			context.put("hCalendars", hCalendars);
			context.put("version", VERSION);
			context.put("perPage", paramter.getLimit()); // TODO デフォルト値のとき表示されない
			context.put("pageNum", paramter.getPageNum());
		} catch (Exception e) {
			logger.error(e);
			template = getTemplate("/apivm/hCalendar.vm");
			context.put("version", VERSION);
			context.put("perPage", 0);
			context.put("pageNum", 0);

		}
		return template;

	}


}
