package controller;

import model.GameLogic;
import ui.*;

// import javax.swing.*;

public class GameController {
    private GameFrame frame;
    private GameLogic logic = new GameLogic();

    public GameController() {
        frame = new GameFrame(this);
    }

    public void goHome() {
        frame.setContentPane(new HomeScreen(this));
        frame.revalidate();
    }

    public void startGame(String p1, String p2) {
        logic.resetBoard();
        frame.setContentPane(new GameBoard(this, logic, p1, p2));
        frame.revalidate();
    }
}
