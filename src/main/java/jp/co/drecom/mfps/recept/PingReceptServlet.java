package jp.co.drecom.mfps.recept;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.drecom.mfps.log.Logger;

import org.apache.xmlrpc.XmlRpc;
import org.apache.xmlrpc.XmlRpcServer;

/**
 * PingÇéÛÇØéÊÇÈëãå˚Ç∆Ç»ÇÈ servlet
 *
 * @author Akiko Asami
 */
public class PingReceptServlet extends HttpServlet {

	private Logger logger = new Logger(getClass());

	/*
	 * (îÒ Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	/*
	 * (îÒ Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		XmlRpc.setEncoding("UTF-8"); // TODO ê›íËÉtÉ@ÉCÉãÇ÷
		XmlRpcServer xmlrpc = new XmlRpcServer();

		xmlrpc.addHandler("weblogUpdates", new WebLogUpdatesHandler(request.getRemoteAddr()));
		try {
			byte[] result = xmlrpc.execute(request.getInputStream());
			response.setContentType("text/xml");
			response.setContentLength(result.length);
			OutputStream out = response.getOutputStream();
			out.write(result);
			out.flush();

		} catch (IOException e) {
			logger.warn(e);
		}
	}
}
