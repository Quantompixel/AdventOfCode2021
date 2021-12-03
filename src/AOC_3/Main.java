package AOC_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // System.out.println(fileToIntArray("res/input/AOC_3.txt")[0][2]);
        // binaryDiagnostic(fileToIntArray("res/input/AOC_3.txt"));
        lifeSupportRating(fileToIntArray("res/input/AOC_3.txt"), 0);
        // lifeSupportRatingInverted(fileToIntArray("res/input/AOC_3.txt"), 0);
    }

    static void lifeSupportRating(int[][] input, int pos) {
        if (input[0].length <= 1) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < input.length; i++) {
                for (int j : input[i]) {
                    output.append(j);
                }
            }
            System.out.println(Integer.parseInt(output.toString(), 2));
            return;
        }

        List<String> list = new ArrayList<>();

        int firstNum;
        int sum = sumOfArray(input[pos]);
        // invert numbers down below
        // 101 - 1 prio
        // 010 - 0 prio
        if (sum > 0) firstNum = 0;
        else if (sum < 0) firstNum = 1;
        else firstNum = 0;

        System.out.println("\n" + firstNum);
        print2dArray(input);

        // checks every binary num
        for (int i = 0; i < input[0].length; i++) {
                //more 1s in array -> 1 else -> 0
                StringBuilder outputNum = new StringBuilder();
                for (int j = 0; j < input.length; j++) {
                    if (input[pos][i] != firstNum) break;

                    outputNum.append(input[j][i]);
                }
                if (!outputNum.toString().isBlank()) {
                    list.add(outputNum.toString());
                }
            }
        lifeSupportRating(stringsToIntArr(list), pos + 1);
    }

    static int[][] stringsToIntArr(List<String> list) {
        int[][] output = new int[list.get(0).length()][list.size()];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                output[i][j] = Integer.parseInt(Character.toString(list.get(j).charAt(i)));
            }
        }

        return output;
    }

    static void binaryDiagnostic(int[][] input) {
        StringBuilder gammaString = new StringBuilder();
        StringBuilder epsilonString = new StringBuilder();
        for (int[] ints : input) {
            if (sumOfArray(ints) > ints.length / 2) {
                gammaString.append(1);
                epsilonString.append(0);
            } else {
                gammaString.append(0);
                epsilonString.append(1);
            }
        }

        int gamma = Integer.parseInt(gammaString.toString(), 2);
        int epsilon = Integer.parseInt(epsilonString.toString(), 2);

        System.out.println(gamma * epsilon);
    }

    static int sumOfArray(int[] input) {
        int counter = 0;
        for (int i : input) {
            if (i == 0) counter--;
            if (i == 1) counter++;
        }

        return counter;
    }

    static int[][] fileToIntArray(String path) {
        try {
            Path file = Paths.get(path);
            int lineCount = (int) Files.lines(file).count();
            List<String> lines = Files.readAllLines(file);

            int[][] output = new int[lines.get(0).length()][lineCount];

            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    output[j][i] = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                }
            }
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[1][];
    }

    static void print2dArray(int[][] input) {
        for (int[] ints : input) {
            System.out.print("[ ");
            for (int anInt : ints) {
                System.out.print(anInt + "; ");
            }
            System.out.print(" ]\n");
        }
    }
}
