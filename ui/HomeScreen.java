package ui;

import javax.swing.*;
import java.awt.*;
import controller.GameController;
import utils.SoundPlayer;

public class HomeScreen extends JPanel {
    JComboBox<String> player1Select;
    JComboBox<String> player2Select;

    public HomeScreen(GameController controller) {
        setLayout(new BorderLayout());

        // ----- TITLE -----
        JLabel titleLabel = new JLabel("Tic Tac Naruto");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // ----- BACKGROUND -----
        JLabel background = new JLabel(new ImageIcon("resources/images/naruto_bg.jpg"));
        background.setLayout(new GridBagLayout());

        // ----- PANEL WITH CONTROLS -----
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(400, 200));

        String[] characters = {"Naruto", "Sasuke", "Sakura", "Kakashi"};
        player1Select = new JComboBox<>(characters);
        player2Select = new JComboBox<>(characters);

        JLabel player1Label = new JLabel("Player 1:");
        player1Label.setForeground(Color.WHITE);
        player1Label.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel player2Label = new JLabel("Player 2:");
        player2Label.setForeground(Color.WHITE);
        player2Label.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(player1Label);
        panel.add(player1Select);
        panel.add(player2Label);
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

        SoundPlayer.playBackground("narutoTheme");
    }
}
