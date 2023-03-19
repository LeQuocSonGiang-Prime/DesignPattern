package clock;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnalaogClock extends JPanel implements Runnable {

    Thread t = null;
    boolean running = false;

    public AnalaogClock() {
        super();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // vẽ mặt đồng hồ
        g2.setColor(Color.WHITE);
        g2.fillOval(50, 50, 200, 200);
        g2.setColor(Color.BLACK);
        g2.drawOval(50, 50, 200, 200);

        // vẽ kim giờ
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        int second = Calendar.getInstance().get(Calendar.SECOND);
        int[] pos = calculateHandPosition(hour % 12, minute, second, 60);
        g2.setColor(Color.BLACK);
        g2.drawLine(150, 150, pos[0], pos[1]);

        // vẽ kim phút
        pos = calculateHandPosition(0, minute, second, 95);
        g2.setColor(Color.BLACK);
        g2.drawLine(150, 150, pos[0], pos[1]);

        // vẽ kim giây
        pos = calculateHandPosition(0, 0, second, 95);
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

    public void start() {
        if (t == null) {
            t = new Thread(this);
            running = true;
            t.start();
        }
    }

    public void stop() {
        running = false;
        t = null;
    }

    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        AnalaogClock clock = new AnalaogClock();
        frame.add(clock);
        frame.setVisible(true);
        clock.start();
    }
}
