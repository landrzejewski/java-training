package pl.training.module03;

public class Game {

    public static void main(String[] args) {
        var ticTacToe = new TicTacToe();
        ticTacToe.makeMove(0, 0);
        ticTacToe.makeMove(1, 1);

        ticTacToe.makeMove(1, 0);
        ticTacToe.makeMove(0,2);

        ticTacToe.makeMove(2,0);
        ticTacToe.printBoard();

        System.out.println("Is game over: " + ticTacToe.isGameOver());
        System.out.println("Result: " + ticTacToe.getWinner());

        ticTacToe.reset();

        ticTacToe.printBoard();
    }

}
