package jp.co.drecom.mfps.common;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.co.drecom.mfps.model.dao.impl.WebLogUpdateDaoImplTest;
import junit.framework.JUnit4TestAdapter;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �e�X�g�N���X�̋K��N���X
 * @author Akiko Asami
 */
public class TestBase {

	private ApplicationContext ctx = null;

	/**
	 * JUnit4�ł̃e�X�g�����邽�߂ɂ͕K�{
	 * @return
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestBase.class);
	}

	public ApplicationContext getApplicationContext() {
		if(ctx == null) {
			System.out.println("Spring���֌W����e�X�g�ł���ꍇ�AinitSpring�����s���Ă��������B");
			initSpring();
			return ctx;
		}
		return ctx;
	}

	/**
	 * Spring�̏�����
	 */
	protected void initSpring() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * dbunit�p�̐ڑ�
	 * @return
	 * @throws Exception
	 */
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mfps", "blog", "drecom");

		IDatabaseConnection iConndection = new DatabaseConnection(connection);

		return iConndection;
	}


	/**
	 * connection�̃N���[�Y�����B
	 * @param connection
	 */
	protected void closeConnection(IDatabaseConnection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) { // �e�X�g�Ȃ�Ŗ���
		}
	}

	/**
	 * �t�@�C������ DateSet�����擾����
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	protected IDataSet getDataSet(String fileName) throws Exception {
		URL url = WebLogUpdateDaoImplTest.class.getResource("/dbunit/" + fileName);
		return new FlatXmlDataSet(new FileInputStream(url.getFile()));
	}

}
