package ui;

import javax.swing.*;
import controller.GameController;

public class GameFrame extends JFrame {
    public GameFrame(GameController controller) {
        setTitle("Tic Tac NaruToe");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new HomeScreen(controller));
        setVisible(true);
    }
}
