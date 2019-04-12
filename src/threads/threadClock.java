package threads;

import userinterface.AirportController;

public class threadClock extends Thread {

	private AirportController air;

	public threadClock(AirportController ai) {
		air = ai;
	}

	@Override
	public void run() {

		while (true) {
			try {
				air.clock();
				sleep(1000);
			} catch (InterruptedException ex) {

			}
		}
	}
}
