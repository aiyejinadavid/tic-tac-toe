package model;

public class GameLogic {
    private int[] board = new int[9]; // 0-empty, 1-X, 2-O

    public boolean isCellEmpty(int index) {
        return board[index] == 0;
    }

    public void setMove(int index, int player) {
        board[index] = player;
    }

    public int checkWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] w : winConditions) {
            if (board[w[0]] != 0 &&
                board[w[0]] == board[w[1]] &&
                board[w[1]] == board[w[2]]) {
                return board[w[0]];
            }
        }
        return 0;
    }

    public boolean isDraw() {
        for (int cell : board) {
            if (cell == 0) return false;
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i < board.length; i++) {
            board[i] = 0;
        }
    }
}
