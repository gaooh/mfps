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
	 * 挿入処理
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HReviewDto record) throws DataAccessException;

	/**
	 * 最新のレビューを指定数分、かえす。
	 * @param count
	 * @return
	 */
	public List findByLatestReview(Integer count);

	/**
	 * 総レビュー数を返す
	 * @param count
	 * @return
	 */
	public Long findByReviewCount();
}
