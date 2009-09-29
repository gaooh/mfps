package jp.co.drecom.mfps.model.dao;

import java.util.Date;
import java.util.List;

import jp.co.drecom.mfps.model.dto.HCalendarDto;
import jp.co.drecom.mfps.model.dto.request.HCalendarSearchDto;

import org.springframework.dao.DataAccessException;

/**
*
* Model: hCalendar
*
* @author Akiko Asami
*/
public interface HCalendarDao {

	/**
	 * 挿入処理
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HCalendarDto record) throws DataAccessException;

	/**
	 * イベント情報をランダムで指定数分取得する
	 * @param record
	 * @throws DataAccessException
	 */
	public List findByEventRandom(Integer count);

	/**
	 * 指定日付のイベント情報をランダムで指定数分取得する
	 * @param count
	 * @param date
	 * @return
	 */
	public List findByEventRandom(Integer count, Date date);

	/**
	 * イベント情報を総数を取得する
	 * @return
	 */
	public Long findByEventCount();

	/**
	 * 指定日付のイベント情報を総数を取得する
	 * @param date
	 * @return
	 */
	public Long findByEventCount(Date date);

	/**
	 * 検索条件から対象 hCalendar 情報をかえす。
	 * @param date
	 * @return
	 */
	public List findByCondition(HCalendarSearchDto hCalendarSearch);
}
