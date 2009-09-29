package jp.co.drecom.mfps.model.dao.impl;

import java.util.List;

import jp.co.drecom.mfps.model.dao.HReviewDao;
import jp.co.drecom.mfps.model.dto.HReviewDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HReviewDaoImpl extends SqlMapClientDaoSupport implements HReviewDao {

	public void insert(HReviewDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("HReview.insert", record);
	}

	public List findByLatestReview(Integer count) {
		return (List) getSqlMapClientTemplate().queryForList("HReview.findByLatestReview" , count);
	}

	public Long findByReviewCount() {
		return (Long) getSqlMapClientTemplate().queryForObject("HReview.findByReviewCount" , null);
	}

}
