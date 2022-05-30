import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class board_game {
    // Keep track of player and cpu's positions.
    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> cpuPos = new ArrayList<>();

    // Main function with the graphical representation of the game board and
    // logistics of the game between user and computer.
    public static void main(String[] args) {

        // Visual representation of a Tic Tac Toe board.
        char[][] board = { { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };

        // Visual representation of the board before start.
        printBoard(board);

        // Loop that allows user and computer to play the game until a win, defeat, or
        // draw is announced.
        while (true) {

            // Receives user input.
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9) = ");

            // User's turn, making sure that user puts the correct input and position.
            int userNum = scan.nextInt();
            while (userNum < 1 || userNum > 9) {
                System.out.println("Selection out of bounds!");
                System.out.print("Enter your placement (1-9) = ");
                userNum = scan.nextInt();
            }

            while (playerPos.contains(userNum) || cpuPos.contains(userNum)) {
                System.out.println("Position taken! Enter a correct position");
                System.out.print("Enter your placement (1-9) = ");
                userNum = scan.nextInt();
            }

            placePiece(board, userNum, "player");

            // Check if user won.
            String result = checkWinner();
            if (result.length() > 0) {
                printBoard(board);
                System.out.println(result);
                break;
            }

            // Computer's turn while checking if a position is taken already.
            Random random = new Random();
            int cpuNum = random.nextInt(9) + 1;
            while (playerPos.contains(cpuNum) || cpuPos.contains(cpuNum)) {
                cpuNum = random.nextInt(9) + 1;
            }

            placePiece(board, cpuNum, "cpu");

            // Check if computer won.
            result = checkWinner();
            if (result.length() > 0) {
                printBoard(board);
                System.out.println(result);
                break;
            }

            // Print board.
            printBoard(board);
        }
    }

    /*
     * Print TicTacToe game board accordingly.
     * Example:
     * X|O|X
     * -+-+-
     * X|O|O
     * -+-+-
     * O|X|X
     */

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // Place 'X' or 'O' on the game board, representing user and computer.
    public static void placePiece(char[][] board, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPos.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPos.add(pos);
        }

        // Checks each number, from 1 to 9, and places corresponding symbol to that
        // number that represents the position.
        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    // Check the winner of the game, whether the symbols 'X' or 'O' are aligned
    // horizontally, diagonally, or vertically.
    // Returns announcement of player that won.
    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPos.containsAll(l)) {
                return "You won!";
            } else if (cpuPos.containsAll(l)) {
                return "CPU wins!";
            }
        }
        if (playerPos.size() + cpuPos.size() == 9) {
            return "Draw!";
        }
        return "";
    }
}
