import java.util.Scanner;

public class Connect4 {
    private static int c = 0;
    public static void incrementC() {
        c++;
    }
    public static int getC() {
        return c;
    }
    public static void main(String[] args) {

        int q = 0;
        int a = 0;
        char[][] board = {{'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}};

        printBoard(board);

        int turn = 0;

        int count = 0;
        boolean u = false;
        while (true) {
            if (turn % 2 == 0) {
                if (dropRedDisk(board)) {
                    count++;
                    //System.out.println(Connect4.getC());
                }
                printBoard(board);
                turn++;
            } else {
                if (dropYellowDisk(board)) {
                    count++;
                    //System.out.println(Connect4.getC());
                }
                printBoard(board);
                turn++;
            }
            if (checkWinner(board) != 'D') {
                if (checkWinner(board) == 'R') {
                    System.out.println("Red wins!");
                    break;
                } else if (checkWinner(board) == 'Y') {
                    System.out.println("Yellow wins!");
                    break;
                }
            }

            outerloop:
            if (Connect4.getC() == 42) {
                for (q = 0; q < 14; q += 2) {
                    for (a = 0; a < 7; a++) {
                        if (checkWinner(board) == 'D') {
                            u = true;
                            break outerloop;
                        }
                    }
                }
            }
            if (Connect4.getC() == 42) {
                System.out.println("It's a draw.");
                break;
            }
        }
    }


    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    public static boolean dropRedDisk(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Drop a red disk at column (0-6): ");
        int number1 = scanner.nextInt();
        int column = 2 * number1 + 1;
        if (number1 >= 0 && number1 <= 6 && board[0][column] == ' ') {
            for (int row = 5; row >= 0; row--) {
                if (board[row][column] == ' ') {
                    board[row][column] = 'R';
                    Connect4.incrementC();
                    return true;
                }
            }
        } else {
            System.out.println("Enter a valid input.");
            dropRedDisk(board);
        }
        return false;
    }

    public static boolean dropYellowDisk(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Drop a yellow disk at column (0-6): ");
        int number = scanner.nextInt();
        int column = 2 * number + 1;
        if (number >= 0 && number <= 6 && (board[0][column] == ' ')) {
            for (int row = 5; row >= 0; row--) {
                if (board[row][column] == ' ') {
                    board[row][column] = 'Y';
                    Connect4.incrementC();
                    return true;
                }
            }
        } else {
            System.out.println("Enter a valid input.");
            dropYellowDisk(board);
        }
        return false;
    }

    public static char checkWinner(char[][] board) {
        int row = 0;
        int column = 0;
        for (row = 0; row < 6; row++) {
            for (column = 0; column < 7; column += 2) {
                if ((board[row][column + 1] != ' ') && (board[row][column + 3] != ' ') &&
                        (board[row][column + 5] != ' ') && (board[row][column + 7] != ' ') &&
                        ((board[row][column + 1] == board[row][column + 3]) &&
                                (board[row][column + 3] == board[row][column + 5]) &&
                                (board[row][column + 5] == (board[row][column + 7]))))
                    return board[row][column + 1];
            }
        }
        int j = 0;
        int k = 0;
        for (j = 1; j < 15; j += 2) {
            for (k = 0; k < 3; k++) {
                if ((board[k][j] != ' ') && (board[k + 1][j] != ' ') && (board[k + 2][j] != ' ')
                        && (board[k + 3][j] != ' ') && ((board[k][j] == board[k + 1][j]) &&
                        (board[k + 1][j] == board[k + 2][j]) && (board[k + 2][j] == board[k + 3][j])))
                    return board[k][j];
            }
        }

        for (int l = 0; l < 3; l++) {
            for (int i = 1; i < 9; i += 2) {
                if ((board[l][i] != ' ') && (board[l + 1][i + 2] != ' ') &&
                        (board[l + 2][i + 4] != ' ') && (board[l + 3][i + 6] != ' ') &&
                        ((board[l][i] == board[l + 1][i + 2]) && (board[l + 1][i + 2] == board[l + 2][i + 4])
                                && (board[l + 2][i + 4] == board[l + 3][i + 6])))
                    return board[l][i];
            }
        }
        for (int n = 0; n < 3; n++) {
            for (int m = 7; m < 15; m += 2) {
                if ((board[n][m] != ' ') && (board[n + 1][m - 2] != ' ') &&
                        (board[n + 2][m - 4] != ' ') && (board[n + 3][m - 6] != ' ') &&
                        ((board[n][m]) == (board[n + 1][m - 2]) &&
                                (board[n + 1][m - 2]) == (board[n + 2][m - 4]) &&
                                (board[n + 2][m - 4]) == (board[n + 3][m - 6]))) {
                    return board[n][m];
                }
            }
        }
        return 'D';
    }
}