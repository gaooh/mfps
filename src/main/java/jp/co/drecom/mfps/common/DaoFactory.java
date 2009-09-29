package jp.co.drecom.mfps.common;

import java.util.HashMap;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.model.dao.AdrDao;
import jp.co.drecom.mfps.model.dao.GeoDao;
import jp.co.drecom.mfps.model.dao.HCalendarCategoryDao;
import jp.co.drecom.mfps.model.dao.HCalendarDao;
import jp.co.drecom.mfps.model.dao.HCardDao;
import jp.co.drecom.mfps.model.dao.HReviewDao;
import jp.co.drecom.mfps.model.dao.ItemDao;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dao.NDao;
import jp.co.drecom.mfps.model.dao.OrgDao;
import jp.co.drecom.mfps.model.dao.VoteLinksDao;
import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dao.XFNDao;
import jp.co.drecom.mfps.model.dao.XFNRegDao;
import jp.co.drecom.mfps.model.dao.XFNToRegDao;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * Daoオブジェクトを生成する。
 *
 * @author Akiko Asami
 */
public class DaoFactory {

	private static HashMap<String, Object> daoHashTable = new HashMap<String, Object>();

	private static Logger logger = new Logger(DaoFactory.class);

	private DaoFactory() {
		// インスタンス生成禁止
	}

	public static PlatformTransactionManager getTransactionManager() {
		return (PlatformTransactionManager)helperGetDao("transactionManager");
	}

	public static WebLogUpdateDao getWebLogUpdateDao() {
		return (WebLogUpdateDao)helperGetDao("webLogUpdateDao");
	}

	public static MicroformatsDao getMicroformatsDao() {
		return (MicroformatsDao)helperGetDao("microformatsDao");
	}

	public static HCardDao getHCardDao() {
		return (HCardDao)helperGetDao("hCardDao");
	}

	public static GeoDao getGeoDao() {
		return (GeoDao)helperGetDao("geoDao");
	}

	public static AdrDao getAdrDao() {
		return (AdrDao)helperGetDao("adrDao");
	}

	public static NDao getNDao() {
		return (NDao)helperGetDao("nDao");
	}

	public static OrgDao getOrgDao() {
		return (OrgDao)helperGetDao("orgDao");
	}

	public static HReviewDao getHReviewDao() {
		return (HReviewDao)helperGetDao("hReviewDao");
	}

	public static VoteLinksDao getVoteLinksDao() {
		return (VoteLinksDao)helperGetDao("voteLinksDao");
	}

	public static ItemDao getItemDao() {
		return (ItemDao)helperGetDao("itemDao");
	}

	public static HCalendarDao getHCalendarDao() {
		return (HCalendarDao)helperGetDao("hCalendarDao");
	}

	public static HCalendarCategoryDao getHCalendarCategoryDao() {
		return (HCalendarCategoryDao)helperGetDao("hCalendarCategoryDao");
	}

	public static XFNDao getXFNDao() {
		return (XFNDao)helperGetDao("XFNDao");
	}

	public static XFNRegDao getXFNRegDao() {
		return (XFNRegDao)helperGetDao("XFNRegDao");
	}

	public static XFNToRegDao getXFNToRegDao() {
		return (XFNToRegDao)helperGetDao("XFNToRegDao");
	}

	private static Object helperGetDao(String daoName) {
		Object object = daoHashTable.get(daoName);
		if(object == null) {
			logger.debug("Initialize DAO: " + daoName);
			object = ApplicationContextFactory.getContainer(daoName);
			daoHashTable.put(daoName, object);
		}
		return object;
	}

}
