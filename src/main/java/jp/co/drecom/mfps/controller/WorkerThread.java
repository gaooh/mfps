package jp.co.drecom.mfps.controller;

/**
 * 処理スレッド
 * @author Akiko Asami
 */
public class WorkerThread extends Thread {

	/** チャネル */
	private final Channel channel;

	public WorkerThread(Channel channel) {
        this.channel = channel;
    }

	/*
	 * (非 Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		while (true) { // channelからリクエストを取得し、実行を繰り返す
			Request request = channel.takeRequest();
			request.execute();
		}

	}

}
