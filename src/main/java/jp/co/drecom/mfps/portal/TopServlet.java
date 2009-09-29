package jp.co.drecom.mfps.portal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import jp.co.drecom.mfps.model.module.HCalendarModule;
import jp.co.drecom.mfps.model.module.HReviewModule;

public class TopServlet extends HttpServlet {

	/*
	 * (�� Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	/*
	 * (�� Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String send = request.getParameter("send");
		if(StringUtils.isNotBlank(send)) {
			request.setAttribute("message", "���̂����V�т����j���`");
		}

		HReviewModule reviewModule = new HReviewModule();
		request.setAttribute("hReviewRecency", reviewModule.findByLatestReview(5)); // TODO �l�͐ݒ�t�@�C������

		Long reviewCount = reviewModule.findByReviewCount();
		request.setAttribute("hReviewCount", reviewCount);

		HCalendarModule calendarModule = new HCalendarModule();
		request.setAttribute("hCalendarToday", calendarModule.findByTodayEventRandom(5)); // TODO �l�͐ݒ�t�@�C������
		request.setAttribute("hCalendarTodayCount", calendarModule.findByTodayEventCount());

		request.setAttribute("hCalendarTomorrow", calendarModule.findByTomorrowEventRandom(5)); // TODO �l�͐ݒ�t�@�C������
		request.setAttribute("hCalendarTomorrowCount", calendarModule.findByTomorrowEventCount());

		request.setAttribute("hCalendarRecency", calendarModule.findByLatestEventRandom(5));
		request.setAttribute("hCalendarCount", calendarModule.findByEventCount()); // TODO �l�͐ݒ�t�@�C������

		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

	}
}
