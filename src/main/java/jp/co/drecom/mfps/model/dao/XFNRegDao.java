package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.XFNRegDto;

import org.springframework.dao.DataAccessException;

public interface XFNRegDao {

	/**
	 * ����: �^�O���Ō���
	 * �Ȃ���� null �I�u�W�F�N�g��������
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public XFNRegDto findByName(String name) throws DataAccessException;

}
