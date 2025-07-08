package ui;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    public LogoPanel() {
        setPreferredSize(new Dimension(600, 80));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image tick = new ImageIcon("assets/tick.png").getImage();
        Image toe = new ImageIcon("assets/toe.png").getImage();

        g.drawImage(tick, 30, 10, 60, 60, this);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("TAC", 110, 55);

        g.drawImage(toe, 250, 10, 60, 60, this);
    }
}
