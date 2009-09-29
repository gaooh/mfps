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
	 * �}������
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(WebLogUpdateDto record) throws DataAccessException;

	/**
	 * �p�[�X�����̗D��x���ł��������R�[�h�Ƀv���Z�XID������U��B
	 * �����ΏۂƂȂ�̂͏��������t���O�������Ă��Ȃ��A�������񐔂����ȉ��̂���
	 * �܂��擾������A���R�[�h���b�N�����������擾�ł��Ȃ��悤����B
	 * @return
	 * @throws DataAccessException
	 */
	public void updateForByHighPriorityRecode(Long processId) throws DataAccessException;

	/**
	 * �v���Z�XID�𔭍s����
	 * @return
	 * @throws DataAccessException
	 */
	public Long issueProcessId() throws DataAccessException;

	/**
	 * �v���Z�XID���烌�R�[�h���擾����
	 * @return
	 * @throws DataAccessException
	 */
	public WebLogUpdateDto findByProcessID(Long processId) throws DataAccessException;

	/**
	 * �������s�ƍX�V
	 * @return
	 * @throws DataAccessException
	 */
	public void updateProcessEnd(Long id) throws DataAccessException;
}