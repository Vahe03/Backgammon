import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
       Game game = new Game();

        Dice dice = new Dice();

        int dice1 = 3;
        int dice2 = 3;

        game.draw();
        while (true) {

            Scanner sc = new Scanner(System.in);

            if (game.getMoveCounter() % 2 == 0)
                System.out.println("White's move. Roll?");
            else {
                System.out.println("Black's move. Roll?");
            }

            if (sc.nextLine().equals("y")) {

//                dice1 = dice.roll();
//                dice2 = dice.roll();

                game.play(sc, dice1, dice2);
                game.setMoveCounter(game.getMoveCounter()+1);
            }
        }

    }
}