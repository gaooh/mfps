package jp.co.drecom.mfps.model.dao;

import java.util.List;

import jp.co.drecom.mfps.model.dto.HCalendarCategoryDto;

public interface HCalendarCategoryDao {

	/**
	 * �}������
	 * @param categoryDto
	 */
	public void insert(HCalendarCategoryDto categoryDto);

	/**
	 * �w��HCalendar�̃J�e�S�����X�g��Ԃ�
	 * @param microformatsId
	 * @return
	 */
	public List findByHCalendarId(Long hCalendarId);

}
