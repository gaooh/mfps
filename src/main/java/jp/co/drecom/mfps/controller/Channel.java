package jp.co.drecom.mfps.controller;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import jp.co.drecom.mfps.log.Logger;

// TODO なんかいまいち一応うごいているけど 5ならもってエレンガとにできるはず

public class Channel {

	private static Channel instance = getInstance();

	/** リクエストキュー */
	private final BlockingQueue<Request> requestQueue;

	/** スレッドプール */
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
		if(instance == null) { // TODO 設定ファイルから取得
			instance = new Channel(10);
		}
		return instance;
	}

	/**
	 * すべてのworkerを初期化
	 */
	public void initWorkers() {
		for (int i = 0; i < this.workersThread; i++) { // すべてのworker thread を初期化
			threadPool.add(new WorkerThread(this));
		}
	}

	/**
	 * すべてのworkerをスタートさせる
	 */
	public void startWorkers() {
		for (Iterator itrThread = threadPool.iterator(); itrThread.hasNext() ; ) {
			WorkerThread thread = (WorkerThread) itrThread.next();
			thread.start();
		}
	}

	/**
	 * リクエストをキューにつむ
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
	 * キューからリクエストを取得する
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
