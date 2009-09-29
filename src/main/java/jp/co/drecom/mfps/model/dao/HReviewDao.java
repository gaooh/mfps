package jp.co.drecom.mfps.model.dao;

import java.util.List;

import jp.co.drecom.mfps.model.dto.HReviewDto;

import org.springframework.dao.DataAccessException;


/**
 *
 * Model: HReviewDao
 *
 * @author Akiko Asami
 */
public interface HReviewDao {

	/**
	 * �}������
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HReviewDto record) throws DataAccessException;

	/**
	 * �ŐV�̃��r���[���w�萔���A�������B
	 * @param count
	 * @return
	 */
	public List findByLatestReview(Integer count);

	/**
	 * �����r���[����Ԃ�
	 * @param count
	 * @return
	 */
	public Long findByReviewCount();
}
