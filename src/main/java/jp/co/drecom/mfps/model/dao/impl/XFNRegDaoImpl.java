package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.XFNRegDao;
import jp.co.drecom.mfps.model.dto.XFNRegDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class XFNRegDaoImpl extends SqlMapClientDaoSupport implements XFNRegDao {

	public XFNRegDto findByName(String naem) throws DataAccessException {
		return (XFNRegDto) getSqlMapClientTemplate().queryForObject("XFNReg.findByName", naem);
	}

}
