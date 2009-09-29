package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.XFNToRegDao;
import jp.co.drecom.mfps.model.dto.XFNToRegDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class XFNToRegDaoImpl extends SqlMapClientDaoSupport implements XFNToRegDao {

	public void insert(XFNToRegDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("XFNToReg.insert", record);
	}

}
