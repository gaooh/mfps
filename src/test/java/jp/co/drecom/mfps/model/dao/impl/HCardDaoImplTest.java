package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.HCardDao;
import jp.co.drecom.mfps.model.dto.HCardDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class HCardDaoImplTest extends TestBase {

	private HCardDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getHCardDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("hcard_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_adr_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_org_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("hcard_n_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);
			expectedDataSet = getDataSet("geo_data.xml");
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

		HCardDto hcard = new HCardDto();
		hcard.setMicroformatsId(new Long(1));
		hcard.setEmail("asami@drecom.co.jp");
		hcard.setNickname("ebisen");
		hcard.setUrl("http://www.gaooh.com");

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			//	現在のデータを取得
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable actualTableBefore = databaseDataSet.getTable("hcard");

			dao.insert(hcard);

			//	挿入後のデータを取得;
			databaseDataSet = connection.createDataSet();
	        ITable actualTableAfter = databaseDataSet.getTable("hcard");


	        // 1行増えているか
	        assertEquals(actualTableBefore.getRowCount() + 1, actualTableAfter.getRowCount());

	        // 挿入データは正しいか
	        String sql = "select * from hcard where microformats_id = 1";
	        ITable actualTable = connection.createQueryTable("hcard",sql);
	        assertEquals("ebisen",(actualTable.getValue(0,"nickname").toString()));
	        assertEquals("asami@drecom.co.jp",(actualTable.getValue(0,"email").toString()));
	        assertEquals("http://www.gaooh.com",(actualTable.getValue(0,"url").toString()));

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
	public void findByMicroformatsId() {

		try {

			HCardDto dto = dao.findByMicroformatsId(new Long(0));

			System.out.println(dto);
		} catch (Exception e) {
			e.printStackTrace();
			fail();

		}
	}
}
