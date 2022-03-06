package lesson_2_tic_tac_toe;

import lesson_2_tic_tac_toe.players.Bot;
import lesson_2_tic_tac_toe.players.Player;

public class Main {
    public static void main(String[] args) {
       TicTacToe ticTacToe =  new TicTacToe(
               new Player("Gonzo"),
               new Bot("R2D2")
       );
       ticTacToe.run();
    }
}
