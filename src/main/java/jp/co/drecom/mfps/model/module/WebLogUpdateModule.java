package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;

public class WebLogUpdateModule {

	/**
	 * �����Ώۃf�[�^��1���擾����
	 * @return
	 */
	public WebLogUpdateDto takeTarget() {

		WebLogUpdateDao webLogUpdateDao = DaoFactory.getWebLogUpdateDao();

		Long processId = webLogUpdateDao.issueProcessId();// �v���Z�XID�擾
		webLogUpdateDao.updateForByHighPriorityRecode(processId);// �v���Z�XID�X�V

		WebLogUpdateDto webLogUpdateDto = webLogUpdateDao.findByProcessID(processId); // �v���Z�XID���烌�R�[�h���擾

		return webLogUpdateDto;
	}

	/**
	 * ���������ƍX�V
	 * @return
	 */
	public void complet(WebLogUpdateDto webLogUpdateDto) {

		WebLogUpdateDao webLogUpdateDao = DaoFactory.getWebLogUpdateDao();
		webLogUpdateDao.updateProcessEnd(webLogUpdateDto.getId());
	}
}
