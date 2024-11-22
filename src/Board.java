import java.util.Scanner;

public class Board {

    private final int[] whiteQuan;
    private final int[] blackQuan;

    private int moveCounter;

    public Board() {
        whiteQuan = new int[24];
        blackQuan = new int[24];
        whiteQuan[0] = 15;
        blackQuan[12] = 15;
        moveCounter = 0;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public int getBlackQuan(int n) {
        return blackQuan[n];
    }

    public int getWhiteQuan(int n) {
        return whiteQuan[n];
    }

    public void setBlackQuan(int pos, int n) {
        this.blackQuan[pos] = n;
    }

    public void setWhiteQuan(int pos, int n) {
        this.whiteQuan[pos] = n;
    }

    public void setMoveCounter(int n) {
        this.moveCounter = n;
    }

    public void draw() {
        draw(whiteQuan, blackQuan);
    }


    //    Draw the board
    private void draw(int[] whiteQuan, int[] blackQuan) {

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

    public void moveWhites(int curPos, int newPos) {
        moveWhites(whiteQuan, curPos, newPos);
    }

    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    private void moveWhites(int[] whiteQuan, int curPos, int newPos) {

        whiteQuan[curPos] -= 1;
        if (newPos > curPos && newPos < 23) {
            whiteQuan[newPos] += 1;
        }
        else {
            System.out.println("No such position");
        }

    }

    public void moveBlacks(int curPos, int newPos) {
        moveWhites(whiteQuan, curPos, newPos);
    }

    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    private void moveBlacks(int[] blackQuan, int curPos, int newPos) {

        blackQuan[curPos] -= 1;
        if ((newPos > curPos || newPos < 12) && newPos < 23) {
            blackQuan[newPos] += 1;
            System.out.println("Your piece moved from " + curPos + " to " + newPos);
        }
        else {
            System.out.println("No such position");
        }

    }

    public void play(Scanner sc, int dice1, int dice2) {

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
                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);

                } else if ((newPos - initial) == dice2) {
                    moveWhites(whiteQuan, initial, newPos);
                    headMove = true;
                    dice2 = 0;
                    draw(whiteQuan, blackQuan);
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

                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                } else if ((newPos - initial) == dice2) {
                    moveBlacks(blackQuan, initial, newPos);
                    headMove = true;
                    dice2 = 0;
                    draw(whiteQuan, blackQuan);
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                } else {
                    System.out.println("Illegal move!! Wrong positions");

                }

            }
        }
    }
}
