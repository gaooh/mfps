package jp.co.drecom.mfps.model.dao;

import java.util.List;

import jp.co.drecom.mfps.model.dto.HCalendarCategoryDto;

public interface HCalendarCategoryDao {

	/**
	 * 挿入処理
	 * @param categoryDto
	 */
	public void insert(HCalendarCategoryDto categoryDto);

	/**
	 * 指定HCalendarのカテゴリリストを返す
	 * @param microformatsId
	 * @return
	 */
	public List findByHCalendarId(Long hCalendarId);

}
