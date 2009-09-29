package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.NDto;

import org.springframework.dao.DataAccessException;

/**
 *
 * Model: n
 *
 * @author Akiko Asami
 */
public interface NDao {

	/**
	 * ë}ì¸èàóù
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(NDto record) throws DataAccessException;
}
