import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] whiteQuan = new int[24];
        whiteQuan[0] = 15;

        int[] blackQuan = new int[24];
        blackQuan[12] = 15;

        int moveCounter = 0;

        int dice1 = 0;
        int dice2 = 0;

        draw(whiteQuan, blackQuan);
        while (true) {

            Scanner sc = new Scanner(System.in);

            if (moveCounter % 2 == 0)
                System.out.println("White's move. Roll?");
            else
                System.out.println("Black's move. Roll?");

            if (sc.nextLine().equals("y")) {

                dice1 = roll();
                dice2 = roll();

                play(sc, dice1, dice2, whiteQuan, blackQuan, moveCounter);
                moveCounter++;
            }
        }

    }

    //    Draw the board
    public static void draw(int[] whiteQuan, int[] blackQuan) {

        for(int i = 0; i < 15; i++) {
            System.out.print("=");
        }

//      Upper half
        for (int i = 0; i < 16; i++) {
            System.out.print("\n" + "|");

//          Upper-left part
            for (int j = 11; j > 5; j--) {
                if (whiteQuan[j] > i)
                    System.out.print("o");
                else if (blackQuan[j] > i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }

            System.out.print("|");

//          Upper-right part
            for (int j = 5; j >= 0; j--) {
                if (whiteQuan[j] > i)
                    System.out.print("o");
                else if (blackQuan[j] > i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }

            System.out.print("|");
        }

//      Lower half
        for (int i = 16; i > 0; i--) {
            System.out.print("\n" + "|");

//          Lower-Left part
            for (int j = 12; j < 18; j++) {
                if (whiteQuan[j] >= i)
                    System.out.print("o");
                else if (blackQuan[j] >= i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }

            System.out.print("|");

//          Lower-Right part
            for (int j = 18; j < 24; j++) {
                if (whiteQuan[j] >= i)
                    System.out.print("o");
                else if (blackQuan[j] >= i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }

            System.out.print("|");

        }

        System.out.println();
        for(int i = 0; i < 15; i++) {
            System.out.print("=");
        }
        System.out.println();
    }


    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    public static void moveWhites(int[] whiteQuan, int curPos, int newPos) {

        whiteQuan[curPos] -= 1;
        if (newPos > curPos && newPos < 23) {
            whiteQuan[newPos] += 1;
        }
        else {
            System.out.println("No such position");
        }

    }


    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    public static void moveBlacks(int[] blackQuan, int curPos, int newPos) {

        blackQuan[curPos] -= 1;
        if ((newPos > curPos || newPos < 12) && newPos < 23) {
            blackQuan[newPos] += 1;
            System.out.println("Your piece moved from " + curPos + " to " + newPos);
        }
        else {
            System.out.println("No such position");
        }

    }

    public static void play(Scanner sc, int dice1, int dice2, int[] whiteQuan, int[] blackQuan, int moveCounter) {

        boolean headMove = false;
        int countPairMoves = 0;

        while (!(dice1 == 0 && dice2 == 0)) {

            System.out.println("The numbers on the dice are: " + dice1 + " and " + dice2);
            System.out.println("Input the move:");
            int initial = sc.nextInt();
            int newPos = sc.nextInt();

            if (moveCounter % 2 == 0) {

                if (blackQuan[newPos] != 0) {
                    System.out.println("Illegal move!! Opponent's pieces");
                    continue;
                }

                if (headMove && initial == 0) {
                    System.out.println("Illegal move!! Cannot play from the head");
                    continue;
                }

                if (whiteQuan[initial] == 0) {
                    System.out.println("Illegal Move!! No pieces in the given postion");
                    continue;
                }
                if ((newPos - initial) == dice1) {
                    moveWhites(whiteQuan, initial, newPos);
                    headMove = true;

                    dice1 = 0;
//                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);

                } else if ((newPos - initial) == dice2) {
                    moveWhites(whiteQuan, initial, newPos);
                    headMove = true;
                    dice2 = 0;
//                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);

                } else {
                    System.out.println("Illegal move!! Wrong positions");
                }
            } else {

                if (whiteQuan[newPos] != 0) {
                    System.out.println("Illegal move!! Opponent's pieces");
                    continue;
                }

                if (headMove && initial == 12) {
                    System.out.println("Illegal move!! Cannot play from the head");
                    continue;
                }

                if (blackQuan[initial] == 0) {
                    System.out.println("No pieces in the given postion!!");
                    continue;
                }

                if ((newPos - initial) == dice1) {
                    moveBlacks(blackQuan, initial, newPos);
                    headMove = true;
                    dice1 = 0;

//                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                } else if ((newPos - initial) == dice2) {
                    moveBlacks(blackQuan, initial, newPos);
                    headMove = true;
                    dice2 = 0;
//                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                } else {
                    System.out.println("Illegal move!! Wrong positions");

                }

            }
        }
    }

    public static int roll() {
        Random rand = new Random();

        return rand.nextInt(6) + 1;
    }
}