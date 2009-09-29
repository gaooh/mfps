package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.XFNRegDao;
import jp.co.drecom.mfps.model.dto.XFNRegDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Akiko Asami
 */
public class XFNRegDaoImplTest extends TestBase {

	private XFNRegDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getXFNRegDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("xfn_reg_data.xml");
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

			XFNRegDto dto = dao.findByName("friend");

			System.out.println(dto);
			assertEquals(dto.getId(), 0L);
			assertEquals(dto.getName(), "friend");

			// 存在しないタグの場合はnull
			dto = dao.findByName("errorTag");

			assertEquals(dto, null);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

}
