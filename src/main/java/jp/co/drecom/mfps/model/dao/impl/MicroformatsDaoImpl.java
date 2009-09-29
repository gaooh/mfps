package jp.co.drecom.mfps.model.dao.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MicroformatsDaoImpl extends SqlMapClientDaoSupport implements MicroformatsDao {

	public Long insert(MicroformatsDto record) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("Microformats.insert", record);
	}

	public MicroformatsDto findByChecksum(String url, String checksum) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", url);
		map.put("checksum", checksum);

		MicroformatsDto dto = (MicroformatsDto) getSqlMapClientTemplate().queryForObject("Microformats.findByHash", map);
		if(dto == null) {
			dto = new MicroformatsDto();
		}
		return dto;
	}


}
