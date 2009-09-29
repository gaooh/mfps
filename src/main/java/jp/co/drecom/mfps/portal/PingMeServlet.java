package jp.co.drecom.mfps.portal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.drecom.mfps.log.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.XmlRpc;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class PingMeServlet extends HttpServlet {

	private Logger logger = new Logger(getClass());

	private static final String XMLRPC_CLIENT = "http://localhost/mfps/PingReceptAction"; // TODO 外だし

	private static final String XMLRPC_ENCODE = "UTF-8"; // TODO 外だし

	/*
	 * (非 Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = "me";
		String url = request.getParameter("ping_url");

		if(StringUtils.isBlank(url)) { // 空だった無視 TODO もうちょっとまじめにエラー処理
			return ;
		}

		XmlRpcClient client = null;
		Hashtable result = new Hashtable();

		try{
			XmlRpc.setEncoding(XMLRPC_ENCODE);
			client = new XmlRpcClient (XMLRPC_CLIENT);

		} catch(MalformedURLException e){
			logger.warn(e);
			return ;
		}

		Vector<String> vec = new Vector<String>();
		vec.addElement(name);
		vec.addElement(url);

		try {
			result = (Hashtable)client.execute ("weblogUpdates.ping", vec);
			if (result == null) {
				logger.warn("No Result Back from execute");
			}

			logger.info("response: " + result);
		}catch (IOException e){ // 通信エラーなんでありえる
			logger.warn(e);

		}catch (XmlRpcException e){ // 規約違反はよろしくない
			logger.error(e);
		}

		response.sendRedirect("http://mfps.drecom.jp/?send=true");
	}
}
