package pl.training;

import java.util.Arrays;

public class TicTacToe {

    public static final int EMPTY = 0;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 2;

    private static final int[][] winningSequences = {
            {0, 1, 2}, // top row
            {3, 4, 5}, // middle row
            {6, 7, 8}, // bottom row
            {0, 3, 6}, // left column
            {1, 4, 7}, // middle column
            {2, 5, 8}, // right column
            {0, 4, 8}, // main diagonal
            {2, 4, 6}  // anti-diagonal
    };

    private final int[] board = new int[9];

    private int currentPlayer;
    private int winner;
    private boolean gameOver;

    public void reset() {
        Arrays.fill(board, EMPTY);
        currentPlayer = PLAYER_X;
        winner = EMPTY;
        gameOver = false;
    }

    public boolean makeMove(int row, int col) {
        int index = row * 3 + col;
        if (gameOver || !isValidIndex(index) || isFieldTaken(index)) {
            return false;
        }
        board[index] = currentPlayer;
        if (hasPlayerWon(currentPlayer)) {
            winner = currentPlayer;
            gameOver = true;
        } else if (isBoardFull()) {
            winner = EMPTY;
            gameOver = true;
        } else {
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < board.length;
    }

    private boolean isFieldTaken(int index) {
        return board[index] != EMPTY;
    }

    public int[] getBoard() {
        return board.clone();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean hasPlayerWon(int player) {
        for (int[] winningSequence : winningSequences) {
            if (board[winningSequence[0]] == player && board[winningSequence[1]] == player && board[winningSequence[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int cell : board) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public String getBoardAsString() {
        var board = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {
            char mark = switch (this.board[i]) {
                case PLAYER_X -> 'X';
                case PLAYER_O -> 'O';
                default -> ' ';
            };
            board.append(mark);
            if ((i + 1) % 3 == 0) {
                if (i < this.board.length - 1) {
                    board.append("\n---------\n");
                }
            } else {
                board.append(" | ");
            }
        }
        return board.toString();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.reset();
        System.out.println(game.makeMove(0, 0)); // Player X
        System.out.println(game.makeMove(1, 1)); // Player O
        System.out.println(game.makeMove(0, 1)); // Player X
        System.out.println(game.makeMove(1, 0)); // Player O
        System.out.println(game.makeMove(0, 2)); // Player X wins

        System.out.println("Current board state:");
        System.out.println(game.getBoardAsString());

        if (game.isGameOver()) {
            if (game.getWinner() != EMPTY) {
                System.out.println("Player " + (game.getWinner() == PLAYER_X ? "X" : "O") + " wins!");
            } else {
                System.out.println("It's a tie!");
            }
        } else {
            System.out.println("Game is still in progress.");
        }
    }
}
