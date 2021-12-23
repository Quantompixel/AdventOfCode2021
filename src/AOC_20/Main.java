package AOC_20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = inputImage("res/input/AOC_20.txt");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();

        enhanceImage(inputImage(enhanceImage(inputImage("res/input/AOC_20.txt"))));
    }

    static List<String> enhanceImage(List<String> input) {
        List<String> output = new ArrayList<>();
        String inputString = readInput("res/input/AOC_20.txt").get(0);

        for (int row = 0; row < input.size(); row++) {
            String current = input.get(row);
            StringBuilder newRow = new StringBuilder();

            for (int column = 0; column < current.length(); column++) {

                //neighbours
                StringBuilder binaryNum = new StringBuilder();
                for (int y = row-1; y <= row+1; y++) {
                    if (y < 0 || y >= input.size()) {
                        // System.out.println("...");
                        binaryNum.append("000");
                        continue;
                    }

                    for (int x = column-1; x <= column+1; x++) {
                        if (x < 0 || x >= input.get(y).length()) {
                            // System.out.print('.');
                            binaryNum.append("0");
                            continue;
                        }

                        String app = input.get(y).charAt(x) == '#' ? "1" : "0";
                        binaryNum.append(app);
                        // System.out.print(input.get(y).charAt(x));
                    }
                    // System.out.println();
                }
                int index = Integer.parseInt(binaryNum.toString(),2);
                newRow.append(inputString.charAt(index));
                // System.out.println(index);
                // System.out.println();
            }

            output.add(newRow.toString());
        }

        int lit = 0;
        for (String s : output) {
            System.out.println(s);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#') lit++;
            }
        }
        System.out.println(lit + "\n");

        return output;
    }

    static List<String> inputImage(String path) {
        List<String> input = readInput(path);
        List<String> output = new ArrayList<>();
        String filler = createFillerString(input.get(2).length() + 2);

        output.add(filler);
        for (int i = 2; i < input.size(); i++) {
            output.add('.' + input.get(i) + '.');
        }
        output.add(filler);

        return output;
    }

    static List<String> inputImage(List<String> input) {
        List<String> output = new ArrayList<>();
        String filler = createFillerString(input.get(0).length() + 2);

        output.add(filler);
        for (String s : input) {
            output.add("." + s + ".");
        }
        output.add(filler);

        return output;
    }


    static String createFillerString(int length) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < length; i++) {
            output.append('.');
        }

        return output.toString();
    }

    static List<String> readInput(String path) {
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
