package jp.co.drecom.mfps.model.dao.impl;

import java.util.List;

import jp.co.drecom.mfps.model.dao.HCalendarCategoryDao;
import jp.co.drecom.mfps.model.dto.HCalendarCategoryDto;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HCalendarCategoryDaoImpl extends SqlMapClientDaoSupport implements HCalendarCategoryDao {

	public void insert(HCalendarCategoryDto record) {
		getSqlMapClientTemplate().insert("HCalendarCategory.insert", record);
	}

	public List findByHCalendarId(Long hCalendarId) {
		return ( List ) getSqlMapClientTemplate().queryForList("HCalendarCategory.findByHCalendarId" , hCalendarId);
	}

}
