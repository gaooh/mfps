package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Timestamp;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;
import jp.co.drecom.mfps.util.TimestampUtil;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class WebLogUpdateDaoImplTest extends TestBase {

	private WebLogUpdateDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getWebLogUpdateDao();

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
	 * 追加成功時
	 */
	@Test
	public void insertSuccess() {

		WebLogUpdateDto weblogupdate = new WebLogUpdateDto();
		weblogupdate.setName("aaaa");
		weblogupdate.setUrl("http://www.drecom.co.jp");
		weblogupdate.setRegistDate(TimestampUtil.getNowForSQL());
		weblogupdate.setIp("127.0.0.1");

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			//	現在のデータを取得
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable actualTableBefore = databaseDataSet.getTable("weblogupdate");

			dao.insert(weblogupdate);

			//	挿入後のデータを取得;
			databaseDataSet = connection.createDataSet();
	        ITable actualTableAfter = databaseDataSet.getTable("weblogupdate");


	        // 1行増えているか
	        assertEquals(actualTableBefore.getRowCount() + 1, actualTableAfter.getRowCount());

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * 最も処理優先度の高いレコードを返す
	 */
	@Test
	public void findByHighPriorityRecode() {

		try {
			Long processId = dao.issueProcessId();// プロセスID取得

			System.out.println(processId);

			dao.updateForByHighPriorityRecode(processId);// プロセスID更新

			WebLogUpdateDto dto = dao.findByProcessID(processId); // プロセスIDからレコードを取得

	        assertEquals(dto.getName(), "target");
	        assertEquals(dto.getUrl(), "http://www.drecom.jp4");

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


	/**
	 * 処理終了と更新する
	 */
	@Test
	public void updateProcessEnd() {

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			dao.updateProcessEnd(new Long(4));

			//開始日付は更新されているか
			String sql = "select * from weblogupdate where id = 4";
			ITable actualTable = connection.createQueryTable("weblogupdate",sql);

			Timestamp timestamp = (Timestamp) actualTable.getValue(0,"process_end");
	        assertNotNull(timestamp);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
