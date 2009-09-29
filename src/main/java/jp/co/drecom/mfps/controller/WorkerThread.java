package jp.co.drecom.mfps.controller;

/**
 * �����X���b�h
 * @author Akiko Asami
 */
public class WorkerThread extends Thread {

	/** �`���l�� */
	private final Channel channel;

	public WorkerThread(Channel channel) {
        this.channel = channel;
    }

	/*
	 * (�� Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		while (true) { // channel���烊�N�G�X�g���擾���A���s���J��Ԃ�
			Request request = channel.takeRequest();
			request.execute();
		}

	}

}
