package jp.co.drecom.mfps.controller.polling;

import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.TestBase;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class PollingManagerTest extends TestBase {


	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			//WebLogUpdateDao dao = DaoFactory.getWebLogUpdateDao();

			connection = getConnection();

			IDataSet expectedDataSet = getDataSet("weblogupdate_data.xml");

			// テストデータを投入する
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * ノック
	 */
	@Test
	public void knock() {

		try {
			PollingManager manager = PollingManager.getInstance();
			manager.knock();

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		}
	}
}
