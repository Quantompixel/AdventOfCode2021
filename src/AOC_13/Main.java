package AOC_13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // char[][] input = readInput("res/input/AOC_13.txt");
        // for (char[] chars : input) {
            // System.out.println(Arrays.toString(chars));
        // }

        // fold(fold(readPositions("res/input/AOC_13.txt"), 'y', 7), 'x', 5);
        fold(readPositions("res/input/AOC_13.txt"), 'x', 655);
    }

    static Set<int[]> fold(List<int[]> input, char direction, int foldPos) {
        Set<int[]> output = new TreeSet<>((o1, o2) -> {
            if (o1[0] - o2[0] == 0) {
                if (o1[1] - o2[1] == 0) {
                    return 0;
                }else return o1[1] - o2[1];
            }else return o1[0] - o2[0];
        });

        for (int[] pos : input) {
            System.out.println(Arrays.toString(pos));
            int x = pos[0];
            int y = pos[1];

            if (direction == 'y') {
                int[] temp = new int[2];
                if (y > foldPos) temp[1] = convertPos(y, input.size());
                temp[0] = x;
                output.add(temp);
            } else {
                System.out.println("a");
                int[] temp = new int[2];
                if (x > foldPos) temp[0] = convertPos(x, input.size());
                temp[1] = y;
                output.add(temp);
            }
        }

        System.out.println(output.size());
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
            if(line.isBlank()) break;
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
            if(line.isBlank()) break;
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
}
