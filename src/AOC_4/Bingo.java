package AOC_4;

import java.util.Arrays;
import java.util.Scanner;

public class Bingo {
    int size;
    int[][] board;
    int sumOfBoard;
    int marked = 0;

    public Bingo(int size, String board) {
        this.size = size;
        this.board = constructBoard(board);
        this.sumOfBoard = 0;
    }

    int[][] constructBoard(String board) {
        int[][] output = new int[5][5];
        Scanner scanner = new Scanner(board);

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                int current = scanner.nextInt();
                output[i][j] = current;
                sumOfBoard += current;
            }
        }

        return output;
    }

    public boolean input(int input) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int current = board[i][j];
                if (current == input && current < 100) {
                    current += 100;
                    board[i][j] = current;
                    marked++;
                }
            }
            System.out.println(Arrays.toString(board[i]));
        }

        if (marked >= 5) {
            System.out.println(calculateSum());
            return checkForWin();
        }

        return false;
    }

    boolean checkForWin() {
        for (int i = 0; i < board.length; i++) {
            int rowMarks = 0;
            int columnMarks = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] >= 100) rowMarks++;
                if (board[j][i] >= 100) columnMarks++;
            }
            if (rowMarks >= 5 || columnMarks >= 5) {
                return true;
            }
        }
        return false;
    }

    int calculateSum() {
        int sum = 0;
        for (int[] ints : board) {
            for (int num : ints) {
                if (num < 100) {
                    sum += num;
                }
            }
        }

        return sum;
    }
}
