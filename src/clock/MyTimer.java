package clock;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class MyTimer extends Observable implements Runnable {
	private int second;
	private int minute;
	private int hour;

	public MyTimer() {

	}

	public int getSecond() {
		return second;
	}

	public void myTimeChange() {
		setChanged();
		notifyObservers();
	}

	public void setTime(int s, int m, int h) {
		this.hour = h;
		this.minute = m;
		this.second = s;
		myTimeChange();
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public void run() {

		while (true) {
			try {
				LocalDateTime nowTime = LocalDateTime.now();
				setTime(nowTime.getSecond(), nowTime.getMinute(), nowTime.getHour());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
