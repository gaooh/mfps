package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.XFNToRegDto;

import org.springframework.dao.DataAccessException;

public interface XFNToRegDao {

	/**
	 * �}������
	 *
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(XFNToRegDto record) throws DataAccessException;
}
