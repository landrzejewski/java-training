package pl.training.module03;

import java.util.Arrays;

public class TicTacToe {

    /*private static final byte EMPTY = 0;
    private static final byte PLAYER_X = 1;
    private static final byte PLAYER_O = 2;*/

    private enum FieldValue { X, O, EMPTY }
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

    /*private final byte[] board = new byte[9];
    private byte currentPlayer = PLAYER_X;
    private byte winner = EMPTY;*/
    private final FieldValue[] board = new FieldValue[9];
    private FieldValue currentFieldValue = FieldValue.X;
    private FieldValue winnerValue = FieldValue.EMPTY;
    private boolean isGameOver = false;

    public TicTacToe() {
        reset();
    }

    public void printBoard() {
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            switch (board[fieldIndex]) {
                case EMPTY:
                    System.out.print("- ");
                    break;
                case X:
                    System.out.print("X ");
                    break;
                case O:
                    System.out.print("O ");
                    break;
            }
            if ((fieldIndex + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }

    public boolean makeMove(int column, int row) {
        var fieldIndex = row * 3 + column;
        if (isGameOver || !isOnBoard(fieldIndex) || !isFree(fieldIndex)) {
            return false;
        }
        board[fieldIndex] = currentFieldValue;
        if (hasPlayerWon()) {
            winnerValue = currentFieldValue;
            isGameOver = true;
        } else if (isBoardFull()) {
            isGameOver = true;
        } else {
            togglePlayer();
        }
        return true;
    }

    private boolean isOnBoard(int fieldIndex) {
        return fieldIndex >= 0 && fieldIndex < board.length;
    }

    private boolean isFree(int fieldIndex) {
        return board[fieldIndex] == FieldValue.EMPTY;
    }

    private void togglePlayer() {
        if (currentFieldValue == FieldValue.X) {
            currentFieldValue = FieldValue.O;
        } else {
            currentFieldValue = FieldValue.X;
        }
    }

    private boolean isBoardFull() {
        for (FieldValue field : board) {
            if (field == FieldValue.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPlayerWon() {
        for (int[] winningSequence : winningSequences) {
            if (board[winningSequence[0]] == currentFieldValue
                    && board[winningSequence[1]] == currentFieldValue
                    && board[winningSequence[2]] == currentFieldValue) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        Arrays.fill(board, FieldValue.EMPTY);
        currentFieldValue = FieldValue.X;
        winnerValue = FieldValue.EMPTY;
        isGameOver = false;
    }

    public String getWinner() {
        return switch (winnerValue) {
            case X -> "X wins";
            case O -> "O wins";
            default -> "Draw";
        };
    }

    public boolean isGameOver() {
        return isGameOver;
    }

}
