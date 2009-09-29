package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.AdrDao;
import jp.co.drecom.mfps.model.dto.AdrDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class AdrDaoImpl extends SqlMapClientDaoSupport implements AdrDao {

	public Long insert(AdrDto record) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("Adr.insert", record);
	}

}
