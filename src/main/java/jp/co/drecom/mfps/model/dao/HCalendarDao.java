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
	 * �}������
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HCalendarDto record) throws DataAccessException;

	/**
	 * �C�x���g���������_���Ŏw�萔���擾����
	 * @param record
	 * @throws DataAccessException
	 */
	public List findByEventRandom(Integer count);

	/**
	 * �w����t�̃C�x���g���������_���Ŏw�萔���擾����
	 * @param count
	 * @param date
	 * @return
	 */
	public List findByEventRandom(Integer count, Date date);

	/**
	 * �C�x���g���𑍐����擾����
	 * @return
	 */
	public Long findByEventCount();

	/**
	 * �w����t�̃C�x���g���𑍐����擾����
	 * @param date
	 * @return
	 */
	public Long findByEventCount(Date date);

	/**
	 * ������������Ώ� hCalendar �����������B
	 * @param date
	 * @return
	 */
	public List findByCondition(HCalendarSearchDto hCalendarSearch);
}
