package ui;

import javax.swing.*;
import java.awt.*;
import controller.GameController;

public class HomeScreen extends JPanel {
    JComboBox<String> player1Select;
    JComboBox<String> player2Select;

    public HomeScreen(GameController controller) {
        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("resources/images/naruto_bg.jpg"));
        background.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(400, 200));

        String[] characters = {"Naruto", "Sasuke", "Sakura", "Kakashi"};
        player1Select = new JComboBox<>(characters);
        player2Select = new JComboBox<>(characters);

        panel.add(new JLabel("Player 1:"));
        panel.add(player1Select);
        panel.add(new JLabel("Player 2:"));
        panel.add(player2Select);

        JButton startBtn = new JButton("Start Game");
        JButton exitBtn = new JButton("Exit Game");

        startBtn.addActionListener(e -> controller.startGame(
                player1Select.getSelectedItem().toString(),
                player2Select.getSelectedItem().toString()
        ));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(startBtn);
        panel.add(exitBtn);

        background.add(panel);
        add(background, BorderLayout.CENTER);
    }
}
