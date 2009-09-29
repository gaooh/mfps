package jp.co.drecom.mfps.model.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.drecom.mfps.model.dao.HCalendarDao;
import jp.co.drecom.mfps.model.dto.HCalendarDto;
import jp.co.drecom.mfps.model.dto.request.HCalendarSearchDto;
import jp.co.drecom.mfps.util.DateUtil;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class HCalendarDaoImpl extends SqlMapClientDaoSupport implements HCalendarDao {

	public void insert(HCalendarDto record) throws DataAccessException {
		getSqlMapClientTemplate().insert("HCalendar.insert", record);
	}

	public List findByEventRandom(Integer count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("date", DateUtil.getFormatDate(DateUtil.getToday(), "yyyy-MM-dd"));

		return (List) getSqlMapClientTemplate().queryForList("HCalendar.findByEventRandom" , map);
	}

	public List findByEventRandom(Integer count, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("date", DateUtil.getFormatDate(date, "yyyy-MM-dd"));

		return (List) getSqlMapClientTemplate().queryForList("HCalendar.findByDateEventRandom" , map);
	}

	public Long findByEventCount() {
		return (Long) getSqlMapClientTemplate().queryForObject("HCalendar.findByEventCount" , null);
	}

	public Long findByEventCount(Date date) {
		String strDate = DateUtil.getFormatDate(date, "yyyy-MM-dd"); // TODO –ˆ‰ñŽw’è‚Í‚ß‚ñ‚Ç‚¤‚Å‚·‚æ
		return (Long) getSqlMapClientTemplate().queryForObject("HCalendar.findByDateEventCount" , strDate);
	}

	public List findByCondition(HCalendarSearchDto hCalendarSearch) {
		return (List) getSqlMapClientTemplate().queryForList("HCalendar.findByCondition" , hCalendarSearch);
	}




}
