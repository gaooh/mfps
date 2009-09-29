package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.VoteLinksDto;

import org.springframework.dao.DataAccessException;

/**
 * Model: VoteLinks
 *
 * @author Akiko Asami
 */
public interface VoteLinksDao {

	/**
	 * ë}ì¸èàóù
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(VoteLinksDto record) throws DataAccessException;

}
