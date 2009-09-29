package jp.co.drecom.mfps.portal.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import jp.co.drecom.mfps.common.TestBase;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HCalendarAPIServletTest extends TestBase {

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("hcalendar_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	@Test
	public void full() {
		String url = "http://mfps.drecom.jp/api/hCalendar";

		WebConversation wc = new WebConversation();
		WebRequest request = new GetMethodWebRequest(url);
		WebResponse response = null;

		try {
			response = wc.getResponse(request);

			/* Header 系のチェック */
			assertEquals(response.getResponseCode(), new Integer(200)); // HTMLレスポンスコードは正常
			assertEquals(response.getCharacterSet(), "UTF-8");
			assertEquals(response.getContentType(), "text/xml");

			String text = response.getText();

			System.out.println(text);

			assertTrue(true);

		} catch (Throwable e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void category() {

		String category = "";
		try {
			category = URLEncoder.encode("テスト", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			fail();
		}

		String url = "http://mfps.drecom.jp/api/hCalendar?category=" + category;

		WebConversation wc = new WebConversation();
		WebRequest request = new GetMethodWebRequest(url);
		WebResponse response = null;

		try {
			response = wc.getResponse(request);

			/* Header 系のチェック */
			assertEquals(response.getResponseCode(), new Integer(200)); // HTMLレスポンスコードは正常
			assertEquals(response.getCharacterSet(), "UTF-8");
			assertEquals(response.getContentType(), "text/xml");

			String text = response.getText();

			System.out.println(text);

			assertTrue(true);

		} catch (Throwable e) {
			e.printStackTrace();
			fail();
		}

	}

}
