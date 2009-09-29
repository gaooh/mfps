package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.VoteLinksDao;
import jp.co.drecom.mfps.model.dto.VoteLinksDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class VoteLinksDaoImpl extends SqlMapClientDaoSupport implements VoteLinksDao {

	public void insert(VoteLinksDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("VoteLinks.insert", record);
	}

}
