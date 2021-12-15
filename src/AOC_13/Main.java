package AOC_13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        mutlipleFolds("res/input/AOC_13.txt");

        // fold(fold(readPositions("res/input/AOC_13.txt"), 'y', 7), 'x', 5);
        // fold(readPositions("res/input/AOC_13.txt"), 'x', 655);
        // System.out.println(convertPos(5, 14));
    }

    static void mutlipleFolds(String path) {
        String input = "";
        char[][] array = readInput(path);

        try {
            input = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("fold[ ]along[ ]([a-z])[=]([0-9]+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String direction = matcher.group(1);
            int foldPos = Integer.parseInt(matcher.group(2));

            // System.out.println(direction + " : " + foldPos);
            if (direction.equals("x")) {
                array = xFold(array, foldPos);
            } else {
                array = yFold(array, foldPos);
            }

            printArr(array);
        }

        int counter = 0;
        for (char[] chars : array) {
            for (char aChar : chars) {
                if (aChar == '#') counter++;
            }
        }
        // printArr(array);
        // System.out.println(folds);
        System.out.println(counter);
    }

    static char[][] xFold(char[][] input, int foldPos) {
        // System.out.println(foldPos);
        char[][] output = new char[input.length][foldPos];

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                if (column > foldPos) {
                    if (input[row][column] == '#') {
                        output[row][convertPos(column, input[row].length)] = '#';
                    }
                    continue;
                } // else if (row == foldPos) {
                    // continue;
                // }

            if (input[row][column] == '#') output[row][column] = '#';
            }
        }

        return output;
    }

    static char[][] yFold(char[][] input, int foldPos) {
        // System.out.println(foldPos);
        char[][] output = new char[foldPos][input[0].length];

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                if (row > foldPos) {
                    if (input[row][column] == '#') {
                        // System.out.println(convertPos(row, input.length));
                        output[convertPos(row, input.length)][column] = '#';
                    }
                    continue;
                } else if (row == foldPos) {
                    continue;
                }

                if (input[row][column] == '#') output[row][column] = '#';
            }
        }

        return output;
    }

    static int convertPos(int n, int size) {
        return size - 1 - n;
    }

    static List<int[]> readPositions(String path) {
        List<String> lines = new ArrayList<>();
        List<int[]> output = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            if (line.isBlank()) break;
            String[] split = line.split(",");
            int[] pos = new int[2];
            // X
            pos[0] = Integer.parseInt(split[0]);
            // Y
            pos[1] = Integer.parseInt(split[1]);
            output.add(pos);
        }

        return output;
    }

    static char[][] readInput(String path) {
        List<String> lines = new ArrayList<>();
        List<Integer> xPos = new ArrayList<>();
        List<Integer> yPos = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int xMax = 0;
        int yMax = 0;
        for (String line : lines) {
            if (line.isBlank()) break;
            String[] split = line.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (x > xMax) xMax = x;
            if (y > yMax) yMax = y;

            xPos.add(x);
            yPos.add(y);
        }

        char[][] output = new char[yMax + 1][xMax + 1];
        System.out.println(yMax + " : " + xMax);
        for (int i = 0; i < xPos.size(); i++) {
            int x = xPos.get(i);
            int y = yPos.get(i);

            output[y][x] = '#';
        }

        return output;
    }

    static void printArr(char[][] input) {
        for (char[] chars : input) {
            System.out.print("[");
            for (char c : chars) {
                if (c != '#') System.out.print("  ");
                else System.out.print("█ ");
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
