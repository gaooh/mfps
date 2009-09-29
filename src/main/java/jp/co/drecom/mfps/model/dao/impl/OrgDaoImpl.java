package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.OrgDao;
import jp.co.drecom.mfps.model.dto.OrgDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OrgDaoImpl extends SqlMapClientDaoSupport implements OrgDao {

	public Long insert(OrgDto record) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("Org.insert", record);
	}

}
