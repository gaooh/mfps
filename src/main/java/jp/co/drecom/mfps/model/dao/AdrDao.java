package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.AdrDto;

import org.springframework.dao.DataAccessException;

/**
 *
 * Model: adr
 *
 * @author Akiko Asami
 */
public interface AdrDao {

	/**
	 * ë}ì¸èàóù
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(AdrDto record) throws DataAccessException;
}
