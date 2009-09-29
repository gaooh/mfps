package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.XFNDao;
import jp.co.drecom.mfps.model.dto.XFNDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class XFNDaoImplTest extends TestBase {

	private XFNDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getXFNDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("xfn_data.xml");
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

		XFNDto dto = new XFNDto();
		dto.setMicroformatsId(new Long(-1));
		dto.setUrl("http://www.gaogao.com/xfn");

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			dao.insert(dto);

			//	挿入データは正しいか
	        String sql = "select * from xfn where microformats_id = -1 ";

	        ITable actualTable = connection.createQueryTable("xfn",sql);
	        assertEquals("http://www.gaogao.com/xfn",(actualTable.getValue(0,"url").toString()));


		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
