package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.ItemDao;
import jp.co.drecom.mfps.model.dto.ItemDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class ItemDaoImplTest extends TestBase {

	private ItemDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getItemDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("hreview_item_data.xml");
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

		ItemDto item = new ItemDto();
		item.setUrl("http://www.itemuulr/");
		item.setType("mono");
		item.setHcardId(new Long(30));

		IDatabaseConnection connection = null;
		try {

			dao.insert(item);

	        // 挿入データは正しいか
			connection = getConnection();

	        String sql = "select * from hreview_item order by id desc limit 1";
	        ITable actualTable = connection.createQueryTable("item",sql);
	        assertEquals("http://www.itemuulr/",(actualTable.getValue(0,"url").toString()));
	        assertEquals("mono",(actualTable.getValue(0,"type").toString()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
