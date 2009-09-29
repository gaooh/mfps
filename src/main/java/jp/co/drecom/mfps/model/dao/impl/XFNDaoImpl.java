package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.XFNDao;
import jp.co.drecom.mfps.model.dto.XFNDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class XFNDaoImpl extends SqlMapClientDaoSupport implements XFNDao {

	public void insert(XFNDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("XFN.insert", record);
	}

}
