package AOC_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Bingo bingo = new Bingo(5, lines[1]);

        // System.out.println(Arrays.toString(lines));

        playBingo(5);
    }

    static void playBingo(int size) {
        List<Bingo> boards = new ArrayList<>();

        String[] lines = new String[0];
        try {
            lines = Files.readString(Paths.get("res/input/AOC_4.txt")).split("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < lines.length; i++) {
            boards.add(new Bingo(size, lines[i]));
        }

        int boardsThatWon = 0;
        int numOfBoards = lines.length - 1;

        System.out.println(lines[0]);
        String[] randomNumbers = lines[0].split(",");
        for (String randomNumber : randomNumbers) {
            for (Bingo board : boards) {
                if (board.input(Integer.parseInt(randomNumber))) {
                    if (!board.hasWon) {
                        boardsThatWon++;
                        board.hasWon = true;

                        if (boardsThatWon == numOfBoards) {
                            System.out.println("\n\n" + randomNumber);
                            System.out.println(board.calculateSum());
                            return;
                        }
                    }
                }
                System.out.println(board.hasWon);
            }

            System.out.println("-------------------- " + boardsThatWon + " ---------------------");
        }
    }
}
