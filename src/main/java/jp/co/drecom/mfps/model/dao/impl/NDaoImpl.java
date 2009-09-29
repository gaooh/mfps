package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.NDao;
import jp.co.drecom.mfps.model.dto.NDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class NDaoImpl extends SqlMapClientDaoSupport implements NDao {

	public Long insert(NDto record) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("N.insert", record);
	}

}
