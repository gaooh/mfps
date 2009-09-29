package jp.co.drecom.mfps.model.dao.impl;

import jp.co.drecom.mfps.model.dao.ItemDao;
import jp.co.drecom.mfps.model.dto.ItemDto;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ItemDaoImpl extends SqlMapClientDaoSupport implements ItemDao {

	public Long insert(ItemDto record) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("Item.insert", record);
	}

}
