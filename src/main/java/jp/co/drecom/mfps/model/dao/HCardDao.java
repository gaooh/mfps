package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.HCardDto;

import org.springframework.dao.DataAccessException;

/**
 *
 * Model: hCard
 *
 * @author Akiko Asami
 */
public interface HCardDao {

	/**
	 * 挿入処理
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HCardDto record) throws DataAccessException;

	/**
	 * 主キーによる検索
	 * @param record
	 * @throws DataAccessException
	 */
	public HCardDto findByMicroformatsId(Long reviewerId);

	/**
	 * 検索: 詳細
	 * 詳細情報を比べまったく同じものがあるかどうかを検索する
	 * あればそのレコードの ID がかえり、なければ -1 がかえる。
	 * @param record
	 * @throws DataAccessException
	 */
	//public Long findByDetail(HCardDto record) throws DataAccessException;

}
