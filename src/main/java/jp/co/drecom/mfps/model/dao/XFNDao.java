package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.XFNDto;

import org.springframework.dao.DataAccessException;

public interface XFNDao {

	/**
	 * �}������
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(XFNDto record) throws DataAccessException;
}
