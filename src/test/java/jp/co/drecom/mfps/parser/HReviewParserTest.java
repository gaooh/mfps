package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.HReview;
import jp.co.drecom.mfps.microformats.HReview.HReviewElement;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class HReviewParserTest extends TestBase {

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
	public void sucessHReview() {
		HReviewParser parser = null;

		try {

			Crawler crawler = new Crawler("http://onk.blog.drecom.jp/");
			parser = new HReviewParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			System.out.println(" List size : " + list.size());
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				HReview element = (HReview) iter.next();
				System.out.println("element" + element.toString());

				assertEquals(element.getElementValue(HReviewElement.DTREVIEWED), "20070203T1646++0900");
				assertEquals(element.getElementValue(HReviewElement.TYPE), "product");
			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void sucessHReview2() {
		HReviewParser parser = null;

		try {

			Crawler crawler = new Crawler("http://xml.blog.drecom.jp/archive/21");
			parser = new HReviewParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				HReview element = (HReview) iter.next();
				System.out.println("element" + element.toString());

				//assertEquals(element.getElementValue(HReviewElement.DTREVIEWED), "2007-01-29");
				//assertEquals(element.getElementValue(HReviewElement.RATING), "-1");
			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
