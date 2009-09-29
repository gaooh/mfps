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
	 * �}�����������A���s����ID��Ԃ�
	 * @param record
	 * @throws DataAccessException
	 */
	public Long insert(MicroformatsDto record) throws DataAccessException;

	/**
	 * ����: �n�b�V����URL
	 * �o�^����Ă���n�b�V���Ǝ擾����茟������
	 * ����΋�̏�Ԃ̃I�u�W�F�N�g�i�����l���ݒ肳��ĂȂ�)��������B
	 * TODO ���������݂��Ă���ꍇ�ł��A�K�������������Ƃ͂�����Ȃ�
	 * @param checksum
	 * @return
	 * @throws DataAccessException
	 */
	public MicroformatsDto findByChecksum(String url, String checksum) throws DataAccessException;


}
