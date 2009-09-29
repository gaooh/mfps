package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.XFNRegDto;

import org.springframework.dao.DataAccessException;

public interface XFNRegDao {

	/**
	 * 検索: タグ名で検索
	 * なければ null オブジェクトがかえる
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public XFNRegDto findByName(String name) throws DataAccessException;

}
