package clock;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class AnalogClock extends JFrame implements Observer {
	private int second;
	private int minute;
	private int hour;
	private Observable observable;

	public AnalogClock(Observable ob) {

		this.observable = ob;
		observable.addObserver(this);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void paint(Graphics g) {

		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// vẽ mặt đồng hồ
		g2.setColor(Color.WHITE);
		g2.fillOval(50, 50, 200, 200);
		g2.setColor(Color.BLACK);
		g2.drawOval(50, 50, 200, 200);

		// vẽ kim giờ

		int[] pos = calculateHandPosition(hour % 12, minute, second, 60);
		g2.setColor(Color.BLACK);
		g2.drawLine(150, 150, pos[0], pos[1]);

		// vẽ kim phút
		pos = calculateHandPosition(0, minute , second , 95);
		g2.setColor(Color.BLACK);
		g2.drawLine(150, 150, pos[0], pos[1]);

		// vẽ kim giây
		pos = calculateHandPosition(0, 0, second *60, 95);
		g2.setColor(Color.RED);
		g2.drawLine(150, 150, pos[0], pos[1]);
	}

	 private int[] calculateHandPosition(int handValue, int minuteValue, int secondValue, int handLength) {
	        int[] result = new int[2];
	        double angle = (Math.PI / 2) - ((handValue + (minuteValue / 60.0) + (secondValue / 3600.0)) * (Math.PI / 6));
	        result[0] = (int) (150 + Math.cos(angle) * handLength);
	        result[1] = (int) (150 - Math.sin(angle) * handLength);
	        return result;
	    }

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTimer time = new MyTimer();
					AnalogClock a = new AnalogClock(time);
					a.setVisible(true);
					Thread th = new Thread(time);
					th.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	
	
	@Override
	public void update(Observable o, Object arg) {
		MyTimer time = (MyTimer) o;
		this.hour = time.getHour();
		this.minute = time.getMinute();
		this.second = time.getSecond();
		System.err.println(second);
		repaint();

	}
}
