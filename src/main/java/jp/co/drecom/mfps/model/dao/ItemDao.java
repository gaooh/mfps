package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.ItemDto;

import org.springframework.dao.DataAccessException;

public interface ItemDao {

	/**
	 * ë}ì¸èàóù
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(ItemDto record) throws DataAccessException;
}
