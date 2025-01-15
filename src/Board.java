import java.util.Scanner;

public class Board {

    private final int[] whiteQuan;
    private final int[] blackQuan;

    private int moveCounter;

//    Constructor
    public Board() {
        whiteQuan = new int[24];
        blackQuan = new int[24];
        whiteQuan[0] = 15;
        blackQuan[12] = 15;
        moveCounter = 0;
    }

//    Accessors
    public int getMoveCounter() {
        return moveCounter;
    }

    public int getBlackQuan(int n) {
        return blackQuan[n];
    }

    public int getWhiteQuan(int n) {
        return whiteQuan[n];
    }

//    Mutators
    public void setBlackQuan(int pos, int n) {
        this.blackQuan[pos] = n;
    }

    public void setWhiteQuan(int pos, int n) {
        this.whiteQuan[pos] = n;
    }

    public void setMoveCounter(int n) {
        this.moveCounter = n;
    }


//    No-arg draw method
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

//    moveWhites overloading.
    public void moveWhites(int curPos, int newPos) {
        moveWhites(whiteQuan, curPos, newPos);
    }

    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    private void moveWhites(int[] whiteQuan, int curPos, int newPos) {

        whiteQuan[curPos] -= 1;
        if (newPos > curPos) {
            whiteQuan[newPos] += 1;
        }
        else {
            System.out.println("No such position");
        }

    }

//    moveBlacks overloading.
    public void moveBlacks(int curPos, int newPos) {
        moveBlacks(blackQuan, curPos, newPos);
    }
    //  Receives the quantity lists of both sides, current and new positions, and moves the corresponding piece.
    private void moveBlacks(int[] blackQuan, int curPos, int newPos) {

        blackQuan[curPos] -= 1;
        if ((newPos > curPos || newPos < 12)) {
            blackQuan[newPos] += 1;
        }
        else {
            System.out.println("No such position");
        }

    }
}
