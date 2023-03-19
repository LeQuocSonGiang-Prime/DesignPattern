package clock;

import java.time.LocalDateTime;

public class MyTime implements Runnable{
	
	private int second;
	private int minute;
	private int hour;
	
	public MyTime() {
		LocalDateTime nowTime = LocalDateTime.now();
		second = nowTime.getSecond();
		minute  = nowTime.getMinute();
	}
	
	public void increaseSecond() {
		
		if(second==60) {
			second = 0;
			increaseMunite();
		}else {
			second++;
		}
	}

	private void increaseMunite() {
		if(minute==60) {
			minute = 0;
			increaseHour();
		}else {
			minute++;
		}
		
	}

	private void increaseHour() {
		if(hour==24) {
			hour = 0;
		}else {
			hour++;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		increaseSecond();
	}
	
	
}
