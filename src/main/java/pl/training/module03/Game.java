package pl.training.module03;

public class Game {

    static void main(String[] args) {
        var ticTacToe = new TicTacToe();
        ticTacToe.printGameState();
        ticTacToe.makeMove(0, 0);
        ticTacToe.makeMove(1, 1);
        ticTacToe.makeMove(1, 0);
        ticTacToe.makeMove(0, 2);
        ticTacToe.makeMove(2, 0);
        ticTacToe.printGameState();
        ticTacToe.reset();
    }

}
