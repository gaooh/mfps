package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.util.TimestampUtil;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class MicroformatsDaoImplTest extends TestBase {

	private MicroformatsDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			connection = getConnection();

			IDataSet expectedDataSet = getDataSet("microformats_data.xml");

			// テストデータを投入する
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

			dao = DaoFactory.getMicroformatsDao();

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

		MicroformatsDto dto = new MicroformatsDto();
		dto.setRegistDate(TimestampUtil.getNowForSQL());
		dto.setUpdateDate(TimestampUtil.getNowForSQL());
		dto.setType(1);
		dto.setUrl("http://www.gaogao.com/");
		dto.setChecksum("testtesttest");

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			Long value = dao.insert(dto);

			System.out.println("ID:" + value);

			//	挿入データは正しいか
	        String sql = "select * from microformats order by regist_date limit 1";
	        ITable actualTable = connection.createQueryTable("microformats",sql);
	        assertEquals("http://www.gaogao.com/",(actualTable.getValue(0,"url").toString()));
	        assertEquals("testtesttest",(actualTable.getValue(0,"hash_code").toString()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * ハッシュコードの検索がうまくいっていか
	 */
	@Test
	public void selectSuccess() {

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

	        MicroformatsDto dto = dao.findByChecksum("http://www.gaooh.com/", "test_hash");

	        if(dto.isEmpty()) { // 取得できなかったアウト
	        	System.out.println("can't sample date");
	        	fail();
	        	return;
	        }
	        assertEquals(dto.getId(), 10);
	        assertEquals(dto.getUrl(), "http://www.gaooh.com/");

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

}
