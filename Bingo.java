/*
Project: Bingo
Name: Samantha Hawco
Editors: Isabella
 */
package bingo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

    static ArrayList<Integer> B = new ArrayList<Integer>();//list of all values for B column
    static ArrayList<Integer> I = new ArrayList<Integer>();//list of all values for I column
    static ArrayList<Integer> N = new ArrayList<Integer>();//list of all values for N column
    static ArrayList<Integer> G = new ArrayList<Integer>();//list of all values for G column
    static ArrayList<Integer> O = new ArrayList<Integer>();//list of all values for O column

    static ArrayList<Integer> spin = new ArrayList<Integer>();//list of all possible numbers
    static ArrayList<Integer> called = new ArrayList<Integer>();//list of numbers called

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] board = new int[5][5];

        do {//repeats whole game if player wants to play again

            createLists();//fills the array lists with appropriate values
            displayBoard(board);//displays the playing board, recieves the 2-d board array
            fillBoard(board);//fills the board with appropriate and random numbers, recieves board array

            do {//repeats number draw and check until win
                checkCard(randomNum(), board);//a random number is generated, removed from spin list and displayed, returns the random number 
                //card is checked for the random number
                displayBoard(board);//displays the playing board, recieves the 2-d board array
                //System.out.println(checkWin(board));
            } while (checkWin(board) == false);//is the main method for the win methods, recieves board array, returns boolean
        } while (askAgain());//asks if the player would like to play again.
    }

    public static void createLists() {//fills the array lists with appropriate values

        for (int i = 1; i < 16; i++) {
            B.add(i);
        }
        for (int i = 16; i < 31; i++) {
            I.add(i);
        }
        for (int i = 31; i < 46; i++) {
            N.add(i);
        }
        for (int i = 46; i < 61; i++) {
            G.add(i);
        }
        for (int i = 61; i < 76; i++) {
            O.add(i);
        }

        for (int i = 1; i < 76; i++) {
            spin.add(i);
        }

    }

    public static void fillBoard(int[][] board) {//fills the board with appropriate and random numbers, recieves board array 
        Random rand = new Random();

        int value;

        for (int j = 0; j < board.length; j++) {//goes through columns
            for (int i = 0; i < board[1].length; i++) {//goes through rows
                switch (j) {
                    case 0:
                        value = rand.nextInt(B.size()) + 0;
                        board[i][j] = B.get(value);
                        B.remove(value);
                        break;
                    case 1:
                        value = rand.nextInt(I.size()) + 0;
                        board[i][j] = I.get(value);
                        I.remove(value);
                        break;
                    case 2:
                        value = rand.nextInt(N.size()) + 0;
                        board[i][j] = N.get(value);
                        N.remove(value);
                        break;
                    case 3:
                        value = rand.nextInt(G.size()) + 0;
                        board[i][j] = G.get(value);
                        G.remove(value);
                        break;
                    case 4:
                        value = rand.nextInt(O.size()) + 0;
                        board[i][j] = O.get(value);
                        O.remove(value);
                        break;
                    default:
                        break;
                }

            }

        }

    }

    public static void displayBoard(int[][] board) {//displays the playing board, recieves the 2-d board array

        System.out.println("B  " + "I  " + "N  " + "G  " + "O");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] < 10) {
                    System.out.print(board[i][j] + "  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("Called:");
        for (int i = 0; i < called.size(); i++) {
            System.out.print(called.get(i) + " ");
        }

        System.out.println("\n ");
    }

    public static int randomNum() {//a random number is generated, removed from spin list and displayed, returns the random number 
        int index, num;
        Random rand = new Random();

        index = rand.nextInt(spin.size()) + 0;
        num = spin.get(index);
        called.add(index);
        spin.remove(index);

        if (num < 16) {
            System.out.println("B" + num);
        } else if (num < 31) {
            System.out.println("I" + num);
        } else if (num < 46) {
            System.out.println("N" + num);
        } else if (num < 61) {
            System.out.println("G" + num);
        } else if (num < 76) {
            System.out.println("O" + num);
        }

        return num;
    }

    public static void checkCard(int num, int[][] board) {//checks for the presence of number on board, recieves number and board array

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] == num) {
                    board[i][j] = 0;
                }
            }
        }

    }
/**
 * 
 * 
 * 
 * @param board
 * @return 
 */
    public static boolean checkWin(int[][] board) {//is the main method for the win methods, recieves board array, returns boolean

        if (checkVer(board) == true || checkHor(board) == true|| checkDi(board) == true) {
            return true;
        }
        return false;
    }

    public static boolean checkDi(int[][] board) {//checks for wins in both diagonal options of the board, recieves board array, returns boolean
        int count = 0,count2=0, j = 0, k = board.length - 1;

        for (int i = 0; i < board[1].length; i++) {
            if (board[j][i] == 0) {
                count = count + 1;
            }
            if (count == 5) {
                return true;
            }
            j = j + 1;
        }

        for (int i = 0; i < board[1].length; i++) {

            if (board[k][i] == 0) {
                count2 = count2 + 1;
            }
            if (count2 == 5) {
                return true;
            }
            k = k - 1;
        }
        return false;
    }

    public static boolean checkVer(int[][] board) {//checks for vertical line wins, recieves board arry, returns boolean
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            count=0;
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] == 0) {
                    count = count + 1;
                }
                if (count == 5) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkHor(int[][] board) {//checks for horizontal line wins, recieves board arry, returns boolean
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            count=0;
            for (int j = 0; j < board[1].length; j++) {
                if (board[j][i] == 0) {
                    count = count + 1;
                }
                if (count == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean askAgain() {//asks if the player would like to play again.
        int ask;

        System.out.println("Enter 1 to play again.");
        ask = in.nextInt();

        if (ask == 1) {
            return true;
        } else {
            return false;
        }
    }
}
