package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.HReviewDao;
import jp.co.drecom.mfps.model.dto.HReviewDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class HReviewDaoImplTest extends TestBase {

	private HReviewDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getHReviewDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("hreview_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * 追加成功時
	 */
	@Test
	public void insertSuccess() {

		HReviewDto review = new HReviewDto();
		review.setMicroformatsId(new Long(-1));
		review.setDescription("des");
		review.setDtreviewed("dtr");
		review.setItemId(new Long(12));
		review.setRating(new Integer(3));
		review.setType("mono");
		review.setSummary("summary");

		IDatabaseConnection connection = null;
		try {

			dao.insert(review);

	        // 挿入データは正しいか
			connection = getConnection();

	        String sql = "select * from hreview where MICROFORMATS_ID = -1";
	        ITable actualTable = connection.createQueryTable("hreview",sql);
	        assertEquals("mono",(actualTable.getValue(0,"type").toString()));
	        assertEquals("des",(actualTable.getValue(0,"description").toString()));
	        assertEquals(new Integer(12),(actualTable.getValue(0,"item_id")));

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * 最新の５件のレビューを取得する
	 */
	@Test
	public void findLatesthReview() {

		IDatabaseConnection connection = null;
		try {

			List reviewList = dao.findByLatestReview(5);

			System.out.println(reviewList.size());

			assertEquals(reviewList.size(), 5);

			for(Iterator itrReviewList = reviewList.iterator() ; itrReviewList.hasNext(); ) {
				HReviewDto dto = (HReviewDto) itrReviewList.next();
				System.out.println(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * 総数を返す
	 */
	@Test
	public void findByReviewCount() {

		try {

			Long count = dao.findByReviewCount();

			System.out.println(count);

			assertEquals(count, 6);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		}
	}
}
