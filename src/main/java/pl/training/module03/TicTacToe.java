package pl.training.module03;

public class TicTacToe {

    private char player = 'X';
    private char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'},
    };
    private boolean isGameEnded;

    public void printGameState() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean makeMove(int row, int col) {
        if (!isValidPosition(row, col) || !isCellFree(row, col) || isGameEnded) {
            return false;
        }
        board[row][col] = player;
        if (hasWinningSequence()) {
            isGameEnded = true;
            System.out.println("Player " + player + " won the game!");
            return true;
        }
        if (isBoardFull()) {
            isGameEnded = true;
            System.out.println("Board is full - draw");
            return true;
        }
        player = player == 'X' ? 'O' : 'X';
        return true;
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col <= 2;
    }

    private boolean isCellFree(int row, int col) {
        return board[row][col] == '-';
    }

    public void reset() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = '-';
            }
        }
        player = 'X';
        isGameEnded = false;
    }

    private boolean hasWinningSequence() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

}
