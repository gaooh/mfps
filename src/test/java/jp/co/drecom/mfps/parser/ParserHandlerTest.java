package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.TestBase;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class ParserHandlerTest extends TestBase {

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			connection = getConnection();

			//テストデータを投入する
			IDataSet expectedDataSet = getDataSet("microformats_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_adr_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_org_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_n_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("geo_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hreview_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hreview_item_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("vote_links_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("xfn_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("xfn_reg_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("xfn_to_reg_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	@Test
	public void parserHandle() {

		try {
			//String location = "http://onk.blog.drecom.jp/";
			String location = "http://xml.blog.drecom.jp/archive/21";
			ProcessHandler cardHanlder = new HCardProcessHandler(location);
			ProcessHandler reviewHandler = new HReviewProcessHandler(location);

			cardHanlder.setNext(reviewHandler);
			cardHanlder.request();

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void parserHandle2() {

		try {
			//String location = "http://2xup.org/log/2005/06/27-0238";
			String location = "http://xml.blog.drecom.jp/archive/26";

			ProcessHandler cardHanlder = new HCardProcessHandler(location);
			ProcessHandler reviewHandler = new HReviewProcessHandler(location);
			ProcessHandler calendarHandler = new HCalendarProcessHandler(location);

			cardHanlder.setNext(reviewHandler).setNext(calendarHandler);
			cardHanlder.request();

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
//
//	@Test
//	public void parserHandle3() {
//
//		try {
//			String location = "http://redmonk.net/tags/microformats";
//			ProcessHandler cardHanlder = new HCardProcessHandler(location);
//			ProcessHandler reviewHandler = new HReviewProcessHandler(location);
//			ProcessHandler calendarHandler = new HCalendarProcessHandler(location);
//			ProcessHandler voteLinksHandler = new VoteLinksProcessHandler(location);
//
//			cardHanlder.setNext(reviewHandler).setNext(calendarHandler).setNext(voteLinksHandler);
//			cardHanlder.request();
//
//			assertTrue(true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
//	}
//
//	@Test
//	public void parserHandle4() {
//
//		try {
//			String location = "http://xml.blog.drecom.jp/archive/24";
//			ProcessHandler cardHanlder = new HCardProcessHandler(location);
//			ProcessHandler reviewHandler = new HReviewProcessHandler(location);
//			ProcessHandler calendarHandler = new HCalendarProcessHandler(location);
//			ProcessHandler voteLinksHandler = new VoteLinksProcessHandler(location);
//			ProcessHandler xfnHandler = new XFNProcessHandler(location);
//
//			cardHanlder.setNext(reviewHandler).setNext(calendarHandler).setNext(voteLinksHandler).setNext(xfnHandler);
//			cardHanlder.request();
//
//			assertTrue(true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
//	}
}
