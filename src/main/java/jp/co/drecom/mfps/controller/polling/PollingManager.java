package jp.co.drecom.mfps.controller.polling;

import jp.co.drecom.mfps.controller.Channel;
import jp.co.drecom.mfps.controller.Request;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;
import jp.co.drecom.mfps.model.module.WebLogUpdateModule;

public class PollingManager {

	private static PollingManager deamon = PollingManager.getInstance();

	private static boolean processing = false;

	/** logger */
	private Logger logger = new Logger(getClass());

	private PollingManager() {
		// Singleton
	}

	public static PollingManager getInstance() {
		if (deamon == null) {
			deamon = new PollingManager();
		}
		return deamon;
	}

	/**
	 * 処理対象が追加されたことノック
	 */
	public void knock() {
		if (isProcessing()) {
			return;
		}
		polling();
	}

	public synchronized boolean isProcessing() {
		if (processing) { // 処理中なら何もせずにまかせておく
			logger.info("polling knock by processing.");
			return true;
		}
		return false;
	}

	public void polling() {
		logger.info("polling start ");
		Thread thread = new Thread(new PollingThread());
		thread.start();
		logger.info("polling end ");
	}

	class PollingThread implements Runnable {

		private Logger logger = new Logger(getClass());

		/* (非 Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			logger.info(" polling thread start ");

			try {
				WebLogUpdateModule updateModuls = new WebLogUpdateModule();
				WebLogUpdateDto webLogUpdateDto = null;

				while(true) { // 処理対象がある場合は

					webLogUpdateDto = updateModuls.takeTarget();
					logger.info(" get target : " + webLogUpdateDto);

					if(webLogUpdateDto.isNotEmpty()) { // リクエスト処理を追加し続ける
						logger.info(" process start : "+ webLogUpdateDto);

						Channel channel = Channel.getInstance();

						Request request = new Request(webLogUpdateDto);
						channel.putRequest(request);
					} else {
						logger.info(" not have target...");

						break;
					}
				}

				processing = false;

			} catch(Exception e) { // 何らかの障害が発生たら致命的
				logger.fatal(e);
			}
		}

	}
}


