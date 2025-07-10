package ui;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import model.GameLogic;
import controller.GameController;
import utils.SoundPlayer;

public class GameBoard extends JPanel {
    JButton[] buttons = new JButton[9];
    JLabel statusLabel = new JLabel();
    JLabel counterLabel = new JLabel("Games Played: 0");
    GameLogic logic;
    GameController controller;
    int gamesPlayed = 0;
    String p1, p2;

    public GameBoard(GameController controller, GameLogic logic, String p1, String p2) {
        this.controller = controller;
        this.logic = logic;
        this.p1 = p1;
        this.p2 = p2;

        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Arial", Font.BOLD, 32));
            int idx = i;
            btn.addActionListener(e -> handleMove(idx));
            buttons[i] = btn;
            grid.add(btn);
        }

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(statusLabel);
        topPanel.add(counterLabel);

        JPanel bottomPanel = new JPanel();
        JButton restart = new JButton("Restart");
        JButton home = new JButton("Home");
        JButton exit = new JButton("Exit");

        restart.addActionListener(e -> restartGame());
        home.addActionListener(e -> controller.goHome());
        exit.addActionListener(e -> System.exit(0));

        bottomPanel.add(restart);
        bottomPanel.add(home);
        bottomPanel.add(exit);

        add(topPanel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        updateStatus();
    }

    private void handleMove(int index) {
        if (logic.board[index] != 0) return;

        logic.board[index] = logic.playerOneTurn ? 1 : 2;
        SoundPlayer.play(logic.playerOneTurn ? "sword" : "shield");

        String player = logic.playerOneTurn ? p1 : p2;
        buttons[index].setIcon(new ImageIcon("resources/images/" + player.toLowerCase() + "_icon.png"));
        buttons[index].setEnabled(false);

        int winner = logic.checkWinner();
        if (winner != 0) {
            gamesPlayed++;
            String name = (winner == 1) ? p1 : p2;
            JOptionPane.showMessageDialog(this,
                    name + " Wins!",
                    "Winner",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("resources/images/" + name.toLowerCase() + "_icon.png"));
            counterLabel.setText("Games Played: " + gamesPlayed);
            disableAll();
            return;
        }

        if (logic.isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            disableAll();
            return;
        }

        logic.playerOneTurn = !logic.playerOneTurn;
        updateStatus();
    }

    private void disableAll() {
        for (JButton btn : buttons) btn.setEnabled(false);
    }

    private void restartGame() {
        logic.resetBoard();
        for (JButton btn : buttons) {
            btn.setEnabled(true);
            btn.setIcon(null);
        }
        updateStatus();
    }

    private void updateStatus() {
        String player = logic.playerOneTurn ? p1 : p2;
        statusLabel.setText("Turn: " + player);
    }
}
