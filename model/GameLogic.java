package model;

public class GameLogic {
    public int[] board = new int[9]; // 0 = empty, 1 = player 1, 2 = player 2
    public boolean playerOneTurn = true;

    public void resetBoard() {
        for (int i = 0; i < 9; i++) board[i] = 0;
        playerOneTurn = true;
    }

    public boolean isDraw() {
        for (int cell : board) if (cell == 0) return false;
        return true;
    }

    public int checkWinner() {
        int[][] winComb = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] wc : winComb) {
            if (board[wc[0]] != 0 && board[wc[0]] == board[wc[1]] && board[wc[1]] == board[wc[2]]) {
                return board[wc[0]];
            }
        }
        return 0;
    }

    public int getBestMove() {
        int bestScore = Integer.MIN_VALUE, move = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = 2;
                int score = minimax(false, 0);
                board[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        return move;
    }

    private int minimax(boolean isMax, int depth) {
        int result = checkWinner();
        if (result == 1) return -10 + depth;
        if (result == 2) return 10 - depth;
        if (isDraw()) return 0;

        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = isMax ? 2 : 1;
                int score = minimax(!isMax, depth + 1);
                board[i] = 0;
                best = isMax ? Math.max(score, best) : Math.min(score, best);
            }
        }
        return best;
    }
}
