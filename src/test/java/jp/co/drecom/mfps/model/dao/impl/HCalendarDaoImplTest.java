package jp.co.drecom.mfps.model.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.model.dao.HCalendarCategoryDao;
import jp.co.drecom.mfps.model.dao.HCalendarDao;
import jp.co.drecom.mfps.model.dto.HCalendarCategoryDto;
import jp.co.drecom.mfps.model.dto.HCalendarDto;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class HCalendarDaoImplTest extends TestBase {

	private HCalendarDao calendarDao = null;
	private HCalendarCategoryDao calendarCategoryDao = null;

	@Before
	public void setup() {
		initSpring();

		IDatabaseConnection connection = null;
		try {
			calendarDao = DaoFactory.getHCalendarDao();
			calendarCategoryDao = DaoFactory.getHCalendarCategoryDao();

			connection = getConnection();

			// テストデータを投入する
			IDataSet expectedDataSet = getDataSet("hcalendar_data.xml");
			DatabaseOperation.CLEAN_INSERT.execute(connection, expectedDataSet);

			expectedDataSet = getDataSet("hcalendar_category_data.xml");
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

		IDatabaseConnection connection = null;
		try {

			HCalendarDto calendar = new HCalendarDto();
			calendar.setMicroformatsId(new Long(-1));
			calendar.setDescription("des");
			calendar.setLocation("locations");
			calendar.setUrl("http://www.drecom.co.jp");
			calendar.setSummary("さまりまり");

			calendarDao.insert(calendar);

			HCalendarCategoryDto category = new HCalendarCategoryDto();
			category.setHcalendarId(new Long(-1));
			category.setUrl("http://gaooooooooooh.com");
			category.setCategory("かてがおり");

			calendarCategoryDao.insert(category);

	        // 挿入データは正しいか
			connection = getConnection();

	        String sql = "select * from hcalendar where MICROFORMATS_ID = -1";
	        ITable actualTable = connection.createQueryTable("hcalendar",sql);
	        assertEquals("http://www.drecom.co.jp",(actualTable.getValue(0,"url").toString()));
	        assertEquals("locations",(actualTable.getValue(0,"location").toString()));
	        assertEquals("さまりまり",(actualTable.getValue(0,"summary").toString()));

	        sql = "select * from hcalendar_category where hcalendar_id = -1";
	        actualTable = connection.createQueryTable("hcalendar_category",sql);
	        assertEquals("http://gaooooooooooh.com",(actualTable.getValue(0,"url").toString()));
	        assertEquals("かてがおり",(actualTable.getValue(0,"category").toString()));

		} catch (Throwable e) {
			e.printStackTrace();
			fail();

		} finally {
			closeConnection(connection);
		}
	}

//	/**
//	 * ランダムに今日のイベントを取得
//	 */
//	@Test
//	public void findByEventRandom() {
//
//		try {
//
//			List events = calendarDao.findByEventRandom(3, DateUtil.getToday());
//
//			System.out.println(events.size());
//	        assertEquals(events.size(),3);
//	        for(Iterator itrEvents = events.iterator() ; itrEvents.hasNext(); ) {
//	        	HCalendarDto dto = (HCalendarDto) itrEvents.next();
//				System.out.println(dto);
//	        }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//
//		}
//	}
//
//	/**
//	 * ランダムに明日のイベントを取得
//	 */
//	@Test
//	public void findByEventTomorrowRandom() {
//
//		try {
//
//			List events = calendarDao.findByEventRandom(3, DateUtil.getTomorrow());
//
//			System.out.println(events.size());
//	        //assertEquals(events.size(),1);
//	        for(Iterator itrEvents = events.iterator() ; itrEvents.hasNext(); ) {
//	        	HCalendarDto dto = (HCalendarDto) itrEvents.next();
//				System.out.println(dto);
//	        }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//
//		}
//	}
//
//	/**
//	 * イベント総数取得
//	 */
//	@Test
//	public void findByEventCount() {
//
//		try {
//
//			Long count = calendarDao.findByEventCount();
//
//			System.out.println(count);
//	        assertEquals(count.longValue(), 6L);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//
//		}
//	}
//
//	/**
//	 * 日付ごとのイベント総数取得
//	 */
//	@Test
//	public void findByDateEventCount() {
//
//		try {
//
//			Long count = calendarDao.findByEventCount(DateUtil.getToday());
//
//			System.out.println(count);
//	        assertEquals(count.longValue(), 3L);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//
//		}
//	}
//
//	/**
//	 * イベント検索
//	 */
//	@Test
//	public void findByCodition() {
//
//		try {
//
//			HCalendarSearchDto search = new HCalendarSearchDto();
//			search.setUrl("http://www.gaooh.com/");
//			search.setDtstart("2007-02-07");
//
//			List events = calendarDao.findByCondition(search);
//
//			System.out.println(events.size());
//	        assertEquals(events.size(), 10);
//	        for(Iterator itrEvents = events.iterator() ; itrEvents.hasNext(); ) {
//	        	HCalendarDto dto = (HCalendarDto) itrEvents.next();
//				System.out.println(dto);
//	        }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//
//		}
//	}
}
