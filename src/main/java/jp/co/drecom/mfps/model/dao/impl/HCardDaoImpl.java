package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.HCardDao;
import jp.co.drecom.mfps.model.dto.HCardDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HCardDaoImpl extends SqlMapClientDaoSupport implements HCardDao {

	public void insert(HCardDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("HCard.insert", record);
	}

	public HCardDto findByMicroformatsId(Long reviewerId) {
		return (HCardDto) getSqlMapClientTemplate().queryForObject("HCard.findByMicroformatsId", reviewerId);
	}

//	public Long findByDetail(HCardDto record) throws DataAccessException {
//		HCardDto dto = (HCardDto) getSqlMapClientTemplate().queryForObject("HCard.findByDetail", record);
//		if(dto == null) {
//			return new Long(-1);
//		} else {
//			return dto.getMicroformatsId();
//		}
//	}

}
