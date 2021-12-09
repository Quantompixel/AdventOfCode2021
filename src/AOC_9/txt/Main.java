package AOC_9.txt;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        findLowPoints("res/input/AOC_9.txt");
    }

    static boolean[][] visited;
    static int summary;

    static void findBasin(List<int[]> input, int row, int column) {
        visited[row][column] = true;

        int height = input.size();
        int width = input.get(0).length;

        summary++;

        if (row + 1 < height && !visited[row + 1][column] && input.get(row + 1)[column] < 9) {
            findBasin(input, row + 1, column);
        }
        if (row - 1 >= 0 && !visited[row - 1][column] && input.get(row - 1)[column] < 9) {
            findBasin(input, row - 1, column);
        }
        if (column + 1 < width && !visited[row][column + 1] && input.get(row)[column + 1] < 9) {
            findBasin(input, row, column + 1);
        }
        if (column - 1 >= 0 && !visited[row][column - 1] && input.get(row)[column - 1] < 9) {
            findBasin(input, row, column - 1);
        }
    }

    static void findLowPoints(String path) {
        List<int[]> input = readInput(path);
        List<Integer> output = new ArrayList<>();
        visited = new boolean[input.size()][input.get(0).length];

        int last = Integer.MAX_VALUE;
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length; x++) {
                if (input.get(y)[x] < last) {
                    // System.out.println(input.get(y)[x] + " : " + checkNeighbours(input, y, x));
                    if (checkNeighbours(input, y, x)) {
                        // System.out.println(input.get(y)[x]);
                        findBasin(input, y, x);

                        for (boolean[] booleans : visited) {
                            for (boolean aBoolean : booleans) {
                                if (aBoolean) {
                                    System.out.print('#');
                                }
                                else System.out.print('.');
                            }
                            System.out.println();
                        }
                        System.out.println(summary);
                        output.add(summary);
                        summary = 0;
                        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    }
                }
            }
        }

        output.sort((o1, o2) -> o2-o1);
        System.out.println(output.get(0) * output.get(1) * output.get(2));
    }

    static boolean checkNeighbours(List<int[]> input, int row, int column) {
        int height = input.size();
        int width = input.get(0).length;
        int posValue = input.get(row)[column];

        if (row + 1 < height) {
            if (input.get(row + 1)[column] <= posValue) return false;
        }
        if (row - 1 >= 0) {
            if (input.get(row - 1)[column] <= posValue) return false;
        }
        if (column + 1 < width) {
            if (input.get(row)[column + 1] <= posValue) return false;
        }
        if (column - 1 >= 0) {
            if (input.get(row)[column - 1] <= posValue) return false;
        }

        return true;
    }

    static List<int[]> readInput(String path) {
        List<int[]> list = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                int[] temp = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    temp[i] = Integer.parseInt(Character.toString(line.charAt(i)));
                }
                list.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(Arrays.toString(list.get(1)));

        return list;
    }
}
