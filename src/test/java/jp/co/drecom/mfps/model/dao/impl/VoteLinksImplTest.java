package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.VoteLinksDao;
import jp.co.drecom.mfps.model.dto.VoteLinksDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class VoteLinksImplTest extends TestBase {

	private VoteLinksDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getVoteLinksDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("vote_links_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}


	/**
	 * 挿入処理がうまくいっているか
	 */
	@Test
	public void insertSuccess() {

		VoteLinksDto dto = new VoteLinksDto();
		dto.setMicroformatsId(new Long(-1));
		dto.setTitle("えびまる");
		dto.setType("vote-vote");
		dto.setUrl("http://www.gaogao.com/vote");

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			dao.insert(dto);

			//	挿入データは正しいか
	        String sql = "select * from vote_links where microformats_id = -1";
	        ITable actualTable = connection.createQueryTable("vote_links",sql);
	        assertEquals("えびまる",(actualTable.getValue(0,"title").toString()));
	        assertEquals("http://www.gaogao.com/vote",(actualTable.getValue(0,"url").toString()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
