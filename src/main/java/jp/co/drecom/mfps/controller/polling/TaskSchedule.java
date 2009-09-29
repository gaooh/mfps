package jp.co.drecom.mfps.controller.polling;

import java.util.Timer;
import java.util.TimerTask;

public class TaskSchedule {

	private static TaskSchedule instance = TaskSchedule.getInstance();

	private TaskSchedule() {
		// Singleton
	}

	public static TaskSchedule getInstance() {
		if(instance == null) {
			instance = new TaskSchedule();
		}
		return instance;
	}

	public void setupAndStart() {
		Timer timer = new Timer();
		timer.schedule(new PollingTask(), 5 * 60 * 1000);
	}

	class PollingTask extends TimerTask {

		@Override
		public void run() {
			PollingManager manger = PollingManager.getInstance();
			manger.knock(); // ˆ—ŠJn‚ğ’Ê’m
		}
	}
}





