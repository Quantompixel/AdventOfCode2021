package AOC_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // getUniqueNumbers("res/input/AOC_8.txt");
        // decode7digitDisplay("res/input/AOC_8.txt");
        System.out.println(Arrays.toString(analyseMapping("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab")));
    }

    static void decode7digitDisplay(String path) {
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         *  0000
         * 1    2
         * 1    2
         *  3333
         * 4    5
         * 4    5
         *  6666
         */

        String[] canditates = new String[7];

        for (String s : input) {
            Scanner scan = new Scanner(s);
            for (int i = 0; i < 10; i++) {
                String current = scan.next();
                switch (current.length()) {
                    case 2: //1
                        canditates[2] += current;
                        canditates[5] += current;
                        break;
                    case 3: //7
                        canditates[0] += current;
                        canditates[2] += current;
                        canditates[5] += current;
                        break;
                    case 4: //4
                        canditates[1] += current;
                        canditates[2] += current;
                        canditates[3] += current;
                        canditates[5] += current;
                        break;
                    case 7: //8
                        break;
                }
            }
            System.out.println(Arrays.toString(canditates));
            break;
        }
    }

    static char[] analyseMapping(String data) {
        String[] dataArr = data.split(" ");

        /**
         *  0000
         * 1    2
         * 1    2
         *  3333
         * 4    5
         * 4    5
         *  6666
         */

        char[] output = new char[6];
        String[] canditates = new String[6];

        for (String current : dataArr) {
            switch (current.length()) {
                case 2: //1
                    canditates[2] = (canditates[2] == null) ? current : canditates[2] + current;
                    canditates[5] = (canditates[5] == null) ? current : canditates[5] + current;
                    break;
                case 3: //7
                    canditates[0] = (canditates[0] == null) ? current : canditates[0] + current;
                    canditates[2] = (canditates[2] == null) ? current : canditates[2] + current;
                    canditates[5] = (canditates[5] == null) ? current : canditates[5] + current;
                    break;
                case 4: //4
                    canditates[1] += current;
                    canditates[2] += current;
                    canditates[3] += current;
                    canditates[5] += current;
                    break;
                case 7: //8
                    break;
            }
        }

        return output;
    }

    static void getUniqueNumbers(String path) {
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> output = new ArrayList<>();
        for (String s : input) {
            Scanner scan = new Scanner(s);
            for (int i = 0; i < 10; i++) {
                scan.next();
            }
            scan.next();
            for (int i = 0; i < 4; i++) {
                String current = scan.next();
                switch (current.length()) {
                    case 2:
                        output.add(current);
                        break;
                    case 3:
                        output.add(current);
                        break;
                    case 4:
                        output.add(current);
                        break;
                    case 7:
                        output.add(current);
                        break;
                }
            }
        }
        System.out.println(output.size());

        // System.out.println(ones + sevens + fours + eights);
        // System.out.println(uniqueDigits.size());
    }
}
