package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.XFNToRegDao;
import jp.co.drecom.mfps.model.dto.XFNToRegDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class XFNToRegDaoImplTest extends TestBase {

	private XFNToRegDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getXFNToRegDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("xfn_to_reg_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}


	/**
	 * タグ名で検索処理がうまくいっているか
	 */
	@Test
	public void findByName() {

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();
			XFNToRegDto dto = new XFNToRegDto();
			dto.setRegId(new Long(3));
			dto.setXfnId(new Long(5));

			dao.insert(dto);

			//挿入データが存在しているか
	        String sql = "select * from xfn_to_reg where reg_id = 3 and xfn_id = 5";

	        ITable actualTable = connection.createQueryTable("xfn_to_reg",sql);
	        assertEquals(actualTable.getRowCount(),1);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
