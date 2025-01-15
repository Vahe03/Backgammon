import java.util.Scanner;

public class Game {
    private Board board;
    private boolean gameEnded = false;

    public Game() {
        board = new Board();
    }

    public void draw() {
        board.draw();
    }

    public boolean getGameEnded() {
        return this.gameEnded;
    }
    public int getMoveCounter() {
        return board.getMoveCounter();
    }

    public void setMoveCounter(int n) {
        board.setMoveCounter(n);
    }
    public void setWhiteQuan(int pos, int n) {board.setWhiteQuan(pos, n);}
    public void setBlackQuan(int pos, int n) {board.setBlackQuan(pos, n);}
    public int getBlackQuan(int n) {
        return board.getBlackQuan(n);
    }

    public int getWhiteQuan(int n) {
        return board.getWhiteQuan(n);
    }

    public void play(Scanner sc, int dice1, int dice2) {

        boolean whiteBearOffAllowed = false;
        boolean blackBearOffAllowed = false;
        int whiteHouseCount = 0;
        int blackHouseCount = 0;
        int headMoves = 0;
        boolean headMoveAllowed = true;
        int movesLeft = 2;
        boolean fourMoves = false;
        if (dice1 == dice2) {
            movesLeft = 4;
            fourMoves = true;
        }
        while (movesLeft != 0) {

            System.out.println("The numbers on the dice are: " + dice1 + " and " + dice2);
            System.out.println("Input the move:");
            int initial = sc.nextInt();
            int newPos = sc.nextInt();

//          Whites move
            if (getMoveCounter() % 2 == 0) {

                if (newPos > 23) {
                    System.out.println("No such position");
                    continue;
                }

                if (board.getBlackQuan(initial) != 0) {
                    System.out.println("Illegal move!! Opponent's pieces");
                    continue;
                }

                if (!headMoveAllowed && initial == 0) {
                    System.out.println("Illegal move!! Cannot play from the head");
                    continue;
                }

                if ((board.getWhiteQuan(initial) == 0)) {
                    System.out.println("Illegal Move!! No pieces in the given postion");
                    continue;
                }

                if (initial == newPos) {
                    if (whiteBearOffAllowed) {
                        setWhiteQuan(initial, getWhiteQuan(initial) - 1);
                        whiteHouseCount--;
                        if (whiteHouseCount == 0) {
                            gameEnded = true;
                            System.out.println("Whites win!! Congratulations!");
                        }
                    }

                    else {
                        System.out.println("Illegal move!! Cannot bear off");
                        continue;
                    }
                }

                if ((newPos - initial) == dice1) {
                    board.moveWhites(initial, newPos);
                    movesLeft--;
                    draw();
                    if (initial == 0)
                        headMoves++;
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                    if ((!fourMoves && headMoves == 1) || !(dice1 == 4 || dice1 == 6 || dice1 == 3) || headMoves == 2 ||
                            (headMoves == 1 && getMoveCounter() != 0)) {
                        headMoveAllowed = false;
                    }
                    if (!(initial > 17) && newPos > 17) {
                        whiteHouseCount++;
                        if (whiteHouseCount == 15)
                            whiteBearOffAllowed = true;
                    }

                } else if ((newPos - initial) == dice2) {
                    board.moveWhites(initial, newPos);
                    if (initial == 0)
                        headMoveAllowed = false;
                    movesLeft--;
                    draw();
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                    if (!(initial > 17) && newPos > 17) {
                        whiteHouseCount++;
                        if (whiteHouseCount == 15)
                            whiteBearOffAllowed = true;
                    }
                } else {
                    System.out.println("Illegal move!! Wrong positions");
                }
            }

//          Blacks move
            else {

                if (newPos > 23) {
                    System.out.println("No such position");
                    continue;
                }

                if (board.getWhiteQuan(newPos) != 0) {
                    System.out.println("Illegal move!! Opponent's pieces");
                    continue;
                }

                if (!headMoveAllowed && initial == 12) {
                    System.out.println("Illegal move!! Cannot play from the head");
                    continue;
                }

                if (board.getBlackQuan(initial) == 0) {
                    System.out.println("No pieces in the given postion!!");
                    continue;
                }

                if (initial == newPos) {
                    if (blackBearOffAllowed) {
                        setBlackQuan(initial, getBlackQuan(initial) - 1);
                        blackHouseCount--;
                        if (blackHouseCount == 0) {
                            gameEnded = true;
                            System.out.println("Blacks win!! Congratulations!");
                        }
                    }
                    else {
                        System.out.println("Illegal move!! Cannot bear off");
                        continue;
                    }
                }

                if ((newPos - initial) == dice1) {
                    board.moveBlacks(initial, newPos);
                    movesLeft--;
                    draw();
                    if (initial == 12)
                        headMoves++;
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                    if ((!fourMoves && headMoves == 1) || !(dice1 == 4 || dice1 == 6 || dice1 == 3) || headMoves == 2 ||
                            (headMoves == 1 && getMoveCounter() != 1)) {
                        headMoveAllowed = false;
                    }
                    if (!(initial > 5 && initial < 12) && (newPos > 5 && newPos < 12)) {
                        blackHouseCount++;
                        if (blackHouseCount == 15)
                            blackBearOffAllowed = true;
                    }
                }

                else if ((newPos - initial) == dice2) {
                    board.moveBlacks(initial, newPos);
                    if (initial == 12)
                        headMoveAllowed = false;
                    movesLeft--;
                    draw();
                    System.out.println("Your piece moved from " + initial + " to " + newPos);
                    if (!(initial > 5 && initial < 12) && (newPos > 5 && newPos < 12)) {
                        blackHouseCount++;
                        if (blackHouseCount == 15)
                            blackBearOffAllowed = true;
                    }
                } else {
                    System.out.println("Illegal move!! Wrong positions");

                }

            }
        }
    }
}
