package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.OrgDto;

import org.springframework.dao.DataAccessException;

/**
 *
 * Model: org
 *
 * @author Akiko Asami
 */
public interface OrgDao {

	/**
	 * ë}ì¸èàóù
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(OrgDto record) throws DataAccessException;
}
