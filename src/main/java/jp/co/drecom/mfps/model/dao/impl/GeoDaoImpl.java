package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.GeoDao;
import jp.co.drecom.mfps.model.dto.GeoDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class GeoDaoImpl extends SqlMapClientDaoSupport implements GeoDao {

	public void insert(GeoDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("Geo.insert", record);
	}

}
