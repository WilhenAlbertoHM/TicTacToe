import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class game_board {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    
    public static void main(String[] args) {
        
        // Visual representation of a Tic Tac Toe board.
        char[][] board = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9) = ");
            
            int playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position");
                System.out.print("Enter your placement (1-9) = ");
                playerPos = scan.nextInt();
            }

            placePiece(board, playerPos, "player");
            
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }
            
            placePiece(board, cpuPos, "cpu");
            printBoard(board);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }    
    }

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
            if (playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins! Sorry :(";
            }
        }
        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "Draw!";
        }
        return "";

    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
        System.out.println();
        }
        
    }

    public static void placePiece(char[][] board, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        } 
        
        switch(pos) {
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
}