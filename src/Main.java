import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
       Board board = new Board();

        Dice dice = new Dice();

        int dice1 = 0;
        int dice2 = 0;

        board.draw();
        while (true) {

            Scanner sc = new Scanner(System.in);

            if (board.getMoveCounter() % 2 == 0)
                System.out.println("White's move. Roll?");
            else
                System.out.println("Black's move. Roll?");

            if (sc.nextLine().equals("y")) {

                dice1 = dice.roll();
                dice2 = dice.roll();

                board.play(sc, dice1, dice2);
                board.setMoveCounter(board.getMoveCounter()+1);
            }
        }

    }
}