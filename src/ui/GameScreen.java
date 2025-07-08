package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.GameLogic;
import sound.SoundPlayer;

public class GameScreen extends JFrame {
    private JButton[] buttons = new JButton[9];
    private JLabel statusLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();

    private String player1Name;
    private String player2Name;

    private int player1Score = 0;
    private int player2Score = 0;
    private boolean playerOneTurn = true;

    private GameLogic logic = new GameLogic();
    private SoundPlayer soundPlayer = new SoundPlayer();

    public GameScreen() {
        getPlayerNames();

        setTitle("Naruto Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new LogoPanel(), BorderLayout.NORTH);

        // Status and score panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(statusLabel);
        infoPanel.add(scoreLabel);
        add(infoPanel, BorderLayout.SOUTH);

        // Grid
        JPanel grid = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Arial", Font.BOLD, 48));
            int idx = i;
            btn.addActionListener(e -> handleMove(idx));
            grid.add(btn);
            buttons[i] = btn;
        }
        add(grid, BorderLayout.CENTER);

        updateStatus();
        updateScore();

        setVisible(true);
    }

    private void getPlayerNames() {
        player1Name = JOptionPane.showInputDialog(this, "Enter Player 1 name (X):");
        player2Name = JOptionPane.showInputDialog(this, "Enter Player 2 name (O):");

        if (player1Name == null || player1Name.isEmpty()) player1Name = "Player 1";
        if (player2Name == null || player2Name.isEmpty()) player2Name = "Player 2";
    }

    private void handleMove(int index) {
        if (!logic.isCellEmpty(index)) return;

        logic.setMove(index, playerOneTurn ? 1 : 2);
        buttons[index].setText(playerOneTurn ? "X" : "O");

        // Play sound
        if (playerOneTurn) {
            soundPlayer.playBladeSound();
        } else {
            soundPlayer.playShieldSound();
        }

        int winner = logic.checkWinner();
        if (winner != 0) {
            if (winner == 1) {
                player1Score++;
                JOptionPane.showMessageDialog(this, player1Name + " wins!");
            } else {
                player2Score++;
                JOptionPane.showMessageDialog(this, player2Name + " wins!");
            }
            updateScore();
            resetBoard();
            return;
        } else if (logic.isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
            return;
        }

        playerOneTurn = !playerOneTurn;
        updateStatus();
    }

    private void updateStatus() {
        statusLabel.setText(
            playerOneTurn ? player1Name + "'s turn (X)" : player2Name + "'s turn (O)"
        );
    }

    private void updateScore() {
        scoreLabel.setText(player1Name + ": " + player1Score + " | " +
                           player2Name + ": " + player2Score);
    }

    private void resetBoard() {
        logic.reset();
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setEnabled(true);
        }
        playerOneTurn = true;
        updateStatus();
    }
}
