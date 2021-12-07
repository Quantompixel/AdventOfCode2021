package AOC_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        avoidHorizontalVents("res/input/AOC_5.txt");
    }

    static void avoidHorizontalVents(String path) {
        String input = readInput(path);

        List<int[]> formattedInput = new ArrayList<>();
        int xMax = 0;
        int yMax = 0;

        Pattern pattern = Pattern.compile("(\\d+)[,](\\d+)\\s[-][>]\\s(\\d+)[,](\\d+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int x1 = Integer.parseInt(matcher.group(1));
            int y1 = Integer.parseInt(matcher.group(2));

            int x2 = Integer.parseInt(matcher.group(3));
            int y2 = Integer.parseInt(matcher.group(4));

            if (x1 > xMax) xMax = x1;
            if (x2 > xMax) xMax = x2;
            if (y1 > xMax) yMax = y1;
            if (y2 > xMax) yMax = y2;

            formattedInput.add(new int[]{x1, y1, x2, y2});
        }

        // System.out.println(xMax + " " + yMax);

        // strangely plus 2 idk why (:
        int[][] output = new int[yMax + 2][xMax + 2];
        for (int[] ints : formattedInput) {
            if (ints[1] == ints[3]) {
                //System.out.println(Arrays.toString(ints));
                output = drawHorLine(output, ints[0], ints[2], ints[1]);
            } else if (ints[0] == ints[2]) {
                output = drawVertLine(output, ints[1], ints[3], ints[0]);
            } else {
                System.out.println(Arrays.toString(ints));
                output = drawDiagonalLine(output, ints[0], ints[2], ints[1], ints[3]);
            }
        }

        int counter = 0;
        for (int[] ints : output) {
            for (int anInt : ints) {
                if (anInt > 1) {
                    counter++;
                }
            }
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(counter);
    }

    static int[][] drawDiagonalLine(int[][] input, int x1, int x2, int y1, int y2) {
        int[][] ouput = input;
        int delta = Math.abs(x1 - x2);

        for (int i = 0; i <= delta; i++) {
            System.out.println(x1 + " : " + y1);
            ouput[y1][x1] = input[y1][x1] + 1;

            if (x1 < x2) x1++;
            else x1--;
            if (y1 < y2) y1++;
            else y1--;

        }

        return ouput;
    }

    static int[][] drawVertLine(int[][] input, int y1, int y2, int x) {
        int[][] output = input;
        int start = Math.min(y1, y2);
        int end = Math.max(y1, y2);

        for (int i = start; i <= end; i++) {
            int current = input[i][x];
            output[i][x] = current + 1;
        }

        return output;
    }

    static int[][] drawHorLine(int[][] input, int x1, int x2, int y) {
        int[][] output = input;
        int start = Math.min(x1, x2);
        int end = Math.max(x1, x2);

        for (int i = start; i <= end; i++) {
            int current = input[y][i];
            output[y][i] = current + 1;
        }

        return output;
    }

    static String readInput(String path) {
        String lines = "";
        try {
            lines = Files.readString(Paths.get(path));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
