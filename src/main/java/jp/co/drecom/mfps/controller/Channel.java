package jp.co.drecom.mfps.controller;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import jp.co.drecom.mfps.log.Logger;

// TODO �Ȃ񂩂��܂����ꉞ�������Ă��邯�� 5�Ȃ�����ăG�����K�Ƃɂł���͂�

public class Channel {

	private static Channel instance = getInstance();

	/** ���N�G�X�g�L���[ */
	private final BlockingQueue<Request> requestQueue;

	/** �X���b�h�v�[�� */
	private final BlockingQueue<WorkerThread> threadPool;

	/** logger */
	private Logger logger = new Logger(getClass());

	private int maxLimit = 10;

	private int workersThread = 0;

	private Channel(int workersThread) {
		this.requestQueue = new ArrayBlockingQueue<Request>(maxLimit);
		this.threadPool = new ArrayBlockingQueue<WorkerThread>(maxLimit);
		this.workersThread = workersThread;
		this.initWorkers();
	}

	public static Channel getInstance() {
		if(instance == null) { // TODO �ݒ�t�@�C������擾
			instance = new Channel(10);
		}
		return instance;
	}

	/**
	 * ���ׂĂ�worker��������
	 */
	public void initWorkers() {
		for (int i = 0; i < this.workersThread; i++) { // ���ׂĂ�worker thread ��������
			threadPool.add(new WorkerThread(this));
		}
	}

	/**
	 * ���ׂĂ�worker���X�^�[�g������
	 */
	public void startWorkers() {
		for (Iterator itrThread = threadPool.iterator(); itrThread.hasNext() ; ) {
			WorkerThread thread = (WorkerThread) itrThread.next();
			thread.start();
		}
	}

	/**
	 * ���N�G�X�g���L���[�ɂ�
	 * @param request
	 */
	public synchronized void putRequest(Request request) {
		logger.debug(" currecnt request size :" + requestQueue.size());
		while (requestQueue.size() >= maxLimit) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.fatal(e);
			}
		}
		requestQueue.add(request);
		notifyAll();
	}

	/**
	 * �L���[���烊�N�G�X�g���擾����
	 * @return
	 */
	public synchronized Request takeRequest() {
		while (requestQueue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.fatal(e);
			}
		}
		Request request = requestQueue.poll();
		notifyAll();
		return request;
	}

}
