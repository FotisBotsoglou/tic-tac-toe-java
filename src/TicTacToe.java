import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    /**
     * An App recreating the classic tic_tac_toe table game.
     */

    // The size of the two-dimensional array/
    private static final int SIZE = 3;

    // a variable to help with if the board at
    // the specific position of the array is empty.
    private static final  int EMPTY = 0;

    // variables to help switch between players.
    private static final  int PLAYER_1 = 1;
    private static  final  int PLAYER_2 = 2;

    // Creating a 3x3 dimension array to make the board of the game.
    private static int[][] board =new int[SIZE][SIZE];
    private static int currentPlayer = PLAYER_1;

    // Printing the board to the standard output.
    public static void printBoard(){
        System.out.println("+"+"-".repeat(11)+"+");
        for (int i = 0; i < SIZE; i++){
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++){
                char playerSymbol = (board[i][j] == PLAYER_1) ? 'X' : ((board[i][j] == PLAYER_2) ? 'O' : ' ');
                System.out.print(playerSymbol+" | ");
            }
            System.out.println();
            System.out.println("+"+"-".repeat(11)+"+");
        }
    }

    // a method to check the validation of the player's selection.
    private static boolean isValidMove(int row, int col){
        return (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY);
    }

    private  static void makeMove(int row, int col){
        board[row][col] = currentPlayer;
    }
    // a method to help swithcing player turns.
    private static void  switchPlayer(){
        currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }
    // methods that checks the conditions in order to determine a winner or a draw.
    private static  boolean checkRows(){
        for (int i = 0; i < SIZE; i++){
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    private static boolean checkCols(){
        for (int i = 0; i < SIZE; i++){
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                return true;
            }
        }
        return false;
    }

    private static  boolean checkDiagonals(){
        return (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]);

    }
    private static boolean checkWinner(){
        return  checkRows() || checkCols() || checkDiagonals();
    }

    private static boolean isBoardFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (board[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    // the actual game that also checks the correctness of users inputs.
    private static  void gameOn(Scanner scanner){
        while (true){
            try {
                printBoard();
                System.out.println("Player: " + currentPlayer + " enter row (0-2)");
                System.out.print("> ");
                int row = scanner.nextInt();

                // A pattern that checks if the string representation of the variable contains exactly one digit.
                if (!String.valueOf(row).matches("^\\d$")){
                    throw new InputMismatchException();
                }
                System.out.println();
                System.out.println("Player: " + currentPlayer + " enter column (0-2)");

                int col = scanner.nextInt();
                if (!String.valueOf(col).matches("^\\d$"))
                    System.out.println("> ");
                System.out.println();
                if (isValidMove(row,col)){
                    makeMove(row,col);
                    if (checkWinner()){
                        printBoard();
                        System.out.println("Player: "+currentPlayer+" wins!");
                        break;
                    }
                    else if (isBoardFull()){
                        printBoard();
                        System.out.println("DRAW!");
                        break;
                    }
                    else switchPlayer();
                }
                else System.out.println("Invalid move! Please try again.");
            }catch (InputMismatchException e){
                // discard the invalid token.
                scanner.nextLine();
                System.out.println("Invalid input!");
            }
        }
    }

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            gameOn(scanner);

        }
    }
}