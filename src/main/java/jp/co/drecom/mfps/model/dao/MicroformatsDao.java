package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.MicroformatsDto;

import org.springframework.dao.DataAccessException;

/**
*
* Model: Microformats
*
* @author Akiko Asami
*/
public interface MicroformatsDao {

	/**
	 * 挿入処理をし、発行したIDを返す
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(MicroformatsDto record) throws DataAccessException;

	/**
	 * 検索: ハッシュとURL
	 * 登録されているハッシュと取得元より検索する
	 * あれば空の状態のオブジェクト（何も値が設定されてない)がかえる。
	 * TODO ただし存在している場合でも、必ずしも同じ情報とはかぎらない
	 * @param checksum
	 * @return
	 * @throws DataAccessException
	 */
	public MicroformatsDto findByChecksum(String url, String checksum) throws DataAccessException;


}
