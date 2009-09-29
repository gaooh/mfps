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

			// �e�X�g�f�[�^�𓊓�����
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * �ǉ�������
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

			//	���݂̃f�[�^���擾
	        IDataSet databaseDataSet = connection.createDataSet();
	        ITable actualTableBefore = databaseDataSet.getTable("weblogupdate");

			dao.insert(weblogupdate);

			//	�}����̃f�[�^���擾;
			databaseDataSet = connection.createDataSet();
	        ITable actualTableAfter = databaseDataSet.getTable("weblogupdate");


	        // 1�s�����Ă��邩
	        assertEquals(actualTableBefore.getRowCount() + 1, actualTableAfter.getRowCount());

		} catch (Exception e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * �ł������D��x�̍������R�[�h��Ԃ�
	 */
	@Test
	public void findByHighPriorityRecode() {

		try {
			Long processId = dao.issueProcessId();// �v���Z�XID�擾

			System.out.println(processId);

			dao.updateForByHighPriorityRecode(processId);// �v���Z�XID�X�V

			WebLogUpdateDto dto = dao.findByProcessID(processId); // �v���Z�XID���烌�R�[�h���擾

	        assertEquals(dto.getName(), "target");
	        assertEquals(dto.getUrl(), "http://www.drecom.jp4");

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}


	/**
	 * �����I���ƍX�V����
	 */
	@Test
	public void updateProcessEnd() {

		IDatabaseConnection connection = null;
		try {

			connection = getConnection();

			dao.updateProcessEnd(new Long(4));

			//�J�n���t�͍X�V����Ă��邩
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
