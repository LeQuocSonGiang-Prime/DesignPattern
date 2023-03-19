package clock;

import java.util.Calendar;

public class Test {
	public static void main(String[] args) {
		MyTimer time = new MyTimer();

		ElClock electricClock = new ElClock(time);
		electricClock.setVisible(true);

		AnalogClock analog = new AnalogClock(time);
		analog.setVisible(true);
		Thread a = new Thread(time);
		a.start();

	}
}
