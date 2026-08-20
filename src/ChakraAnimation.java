import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChakraAnimation extends JPanel implements ActionListener {

    private double angle = 0;
    private float pulse = 0;

    public ChakraAnimation()
    {
        setBackground(Color.BLACK);
        Timer timer = new Timer(16, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;

        float glowAlpha = 0.3f + 0.2f * (float) Math.sin(pulse);
        RadialGradientPaint glow = new RadialGradientPaint(
                new Point(cx, cy), radius * 1.6f,
                new float[]{0f, 1f},
                new Color[]{
                        new Color(1f, 0.4f, 0f, glowAlpha),
                        new Color(1f, 0.4f, 0f, 0f)
                });
        g2.setPaint(glow);
        g2.fillOval(cx - (int) (radius * 1.6), cy - (int) (radius * 1.6), (int) (radius * 3.2), (int) (radius * 3.2));

        g2.translate(cx, cy);
        g2.rotate(angle);

        g2.setColor(new Color(255, 140, 0));
        g2.setStroke(new BasicStroke(4f));
        g2.drawOval(-radius, -radius, radius * 2, radius * 2);

        g2.setStroke(new BasicStroke(3f));
        int spokes = 24;
        for (int i = 0; i < spokes; i++) {
            double a = 2 * Math.PI * i / spokes;
            int x1 = (int) (Math.cos(a) * radius * 0.15);
            int y1 = (int) (Math.sin(a) * radius * 0.15);
            int x2 = (int) (Math.cos(a) * radius);
            int y2 = (int) (Math.sin(a) * radius);
            g2.setColor(new Color(255, 160 + (i % 3) * 20, 0));
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setColor(new Color(255, 200, 60));
        int hub = (int) (radius * 0.18);
        g2.fillOval(-hub, -hub, hub * 2, hub * 2);
        g2.setColor(Color.BLACK);
        g2.drawOval(-hub, -hub, hub * 2, hub * 2);

        g2.rotate(-angle);
        g2.translate(-cx, -cy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.02;
        pulse = (float) (pulse + 0.05);
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chakra Animation");
            ChakraAnimation panel = new ChakraAnimation();

            frame.add(panel);
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}