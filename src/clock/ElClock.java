package clock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.nio.channels.IllegalBlockingModeException;
import java.util.Observable;
import java.util.Observer;

public class ElClock extends JFrame implements Observer{

	private JPanel contentPane;
	
	private Observable observable;
	private int second;
	private int minute;
	private int hour;
	JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTimer time = new MyTimer();
					ElClock frame = new ElClock(time);
					
					frame.setVisible(true);
					Thread a = new Thread(time);a.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ElClock(Observable ob) {
		
		this.observable = ob;
		observable.addObserver(this);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 199, 78);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		 lblNewLabel = new JLabel("00:00:00");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		MyTimer time = (MyTimer) o;
		this.hour = time.getHour();
		this.minute = time.getMinute();
		this.second = time.getSecond();
		
		lblNewLabel.setText( (hour<10?("0"+hour):(this.hour+"")) + ":"
				+ (this.minute<10?("0"+this.minute):(this.minute+"")) + ":" +(this.second<10?("0"+this.second):(this.second+"")) );
	}

}
