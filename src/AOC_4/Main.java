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

        System.out.println(lines[0]);
        String[] randomNumbers = lines[0].split(",");
        for (String randomNumber : randomNumbers) {
            for (Bingo board : boards) {
                if (board.input(Integer.parseInt(randomNumber))) {
                    System.out.println(Arrays.toString(board.board[0]));
                    return;
                }
                System.out.println();
            }

            System.out.println("-----------------------------------------");
        }
    }
}