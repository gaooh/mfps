package jp.co.drecom.mfps.model.dao.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;
import jp.co.drecom.mfps.util.TimestampUtil;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class WebLogUpdateDaoImpl extends SqlMapClientDaoSupport implements WebLogUpdateDao {


	public void insert(WebLogUpdateDto record) throws DataAccessException {
    	getSqlMapClientTemplate().insert("WebLogUpdate.addPing", record);
    }

	public void updateForByHighPriorityRecode(Long processId) throws DataAccessException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("process_start", TimestampUtil.getNowForSQL());
		map.put("process_start_limit", TimestampUtil.beforeMinute(5)); // TODO 設定ファイルから値を取得
		map.put("process_count_limit", new Integer(10));
		map.put("process_id", processId);

		getSqlMapClientTemplate().update("WebLogUpdate.updateForByHighPriorityRecode" , map);
	}

	public void updateProcessEnd(Long id) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("process_end", TimestampUtil.getNowForSQL());
		map.put("id", id);

		getSqlMapClientTemplate().update("WebLogUpdate.updateProcessEnd", map);

	}

	public Long issueProcessId() throws DataAccessException {
		return (Long) getSqlMapClientTemplate().queryForObject("WebLogUpdate.issueProcessId" , null);
	}

	public WebLogUpdateDto findByProcessID(Long processId) throws DataAccessException {

		WebLogUpdateDto dto = (WebLogUpdateDto) getSqlMapClientTemplate().queryForObject("WebLogUpdate.findByProcessId" , processId);

		if(dto == null) {
			dto = new WebLogUpdateDto();
		}
		return dto;
	}

}