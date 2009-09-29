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
	 * �}������
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(HCardDto record) throws DataAccessException;

	/**
	 * ��L�[�ɂ�錟��
	 * @param record
	 * @throws DataAccessException
	 */
	public HCardDto findByMicroformatsId(Long reviewerId);

	/**
	 * ����: �ڍ�
	 * �ڍ׏����ׂ܂������������̂����邩�ǂ�������������
	 * ����΂��̃��R�[�h�� ID ��������A�Ȃ���� -1 ��������B
	 * @param record
	 * @throws DataAccessException
	 */
	//public Long findByDetail(HCardDto record) throws DataAccessException;

}
