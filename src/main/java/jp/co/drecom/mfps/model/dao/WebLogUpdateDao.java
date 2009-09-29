package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;

import org.springframework.dao.DataAccessException;

/**
 * Model: weblogupdate
 *
 * @author Akiko Asami
 */
public interface WebLogUpdateDao {

	/**
	 * 挿入処理
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(WebLogUpdateDto record) throws DataAccessException;

	/**
	 * パース処理の優先度が最も高いレコードにプロセスIDを割り振る。
	 * 処理対象となるのは処理完了フラグがたっていない、かつ処理回数が一定以下のもの
	 * また取得した後、レコードロックをかけ他が取得できないようする。
	 * @return
	 * @throws DataAccessException
	 */
	public void updateForByHighPriorityRecode(Long processId) throws DataAccessException;

	/**
	 * プロセスIDを発行する
	 * @return
	 * @throws DataAccessException
	 */
	public Long issueProcessId() throws DataAccessException;

	/**
	 * プロセスIDからレコードを取得する
	 * @return
	 * @throws DataAccessException
	 */
	public WebLogUpdateDto findByProcessID(Long processId) throws DataAccessException;

	/**
	 * 処理失敗と更新
	 * @return
	 * @throws DataAccessException
	 */
	public void updateProcessEnd(Long id) throws DataAccessException;
}