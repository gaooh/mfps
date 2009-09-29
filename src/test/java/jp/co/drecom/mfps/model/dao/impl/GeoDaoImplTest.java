package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.GeoDao;
import jp.co.drecom.mfps.model.dto.GeoDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class GeoDaoImplTest extends TestBase {

	private GeoDao dao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			dao = DaoFactory.getGeoDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("geo_data.xml");
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

		GeoDto geo = new GeoDto();
		geo.setLatitude("aaaaa");
		geo.setLongitude("bbbbbb");
		geo.setMicroformatsId(new Long(1));

		IDatabaseConnection connection = null;
		try {

			dao.insert(geo);

	        // 挿入データは正しいか
			connection = getConnection();

	        String sql = "select * from geo where microformats_id = 1";
	        ITable actualTable = connection.createQueryTable("geo",sql);
	        assertEquals("aaaaa",(actualTable.getValue(0,"latitude").toString()));
	        assertEquals("bbbbbb",(actualTable.getValue(0,"longitude").toString()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}
}
