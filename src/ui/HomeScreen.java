package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setTitle("Naruto Tic Tac Toe");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Naruto Image
        JLabel picLabel = new JLabel(new ImageIcon("assets/naruto.png"));
        add(picLabel, BorderLayout.CENTER);

        // Logo panel at top
        add(new LogoPanel(), BorderLayout.NORTH);

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(this::startGame);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startGame(ActionEvent e) {
        dispose();
        new GameScreen();
    }
}
