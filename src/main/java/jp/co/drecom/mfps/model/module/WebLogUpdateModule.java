package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.model.dao.WebLogUpdateDao;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;

public class WebLogUpdateModule {

	/**
	 * 処理対象データを1件取得する
	 * @return
	 */
	public WebLogUpdateDto takeTarget() {

		WebLogUpdateDao webLogUpdateDao = DaoFactory.getWebLogUpdateDao();

		Long processId = webLogUpdateDao.issueProcessId();// プロセスID取得
		webLogUpdateDao.updateForByHighPriorityRecode(processId);// プロセスID更新

		WebLogUpdateDto webLogUpdateDto = webLogUpdateDao.findByProcessID(processId); // プロセスIDからレコードを取得

		return webLogUpdateDto;
	}

	/**
	 * 処理完了と更新
	 * @return
	 */
	public void complet(WebLogUpdateDto webLogUpdateDto) {

		WebLogUpdateDao webLogUpdateDao = DaoFactory.getWebLogUpdateDao();
		webLogUpdateDao.updateProcessEnd(webLogUpdateDto.getId());
	}
}
