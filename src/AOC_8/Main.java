package AOC_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // getUniqueNumbers("res/input/AOC_8.txt");
         decode7digitDisplay("res/input/AOC_8.txt");
        // analyseMapping("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab");
    }

    static void decode7digitDisplay(String path) {
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int sum = 0;

        for (String s : input) {
            String[] mapping = analyseMapping(s.split("\\|")[0]);
            // System.out.println(mapping);

            Scanner scanner = new Scanner(s.split("\\|")[1]);
            StringBuilder number = new StringBuilder();

            while (scanner.hasNext()) {
                String current = scanner.next();

                for (int i = 0; i < mapping.length; i++) {
                    if (mapping[i].equals(sortAlphabetically(current))) {
                        number.append(i);
                        break;
                    }
                }
            }
            // System.out.println(number);
            sum += Integer.parseInt(number.toString());
        }

        System.out.println("Summe: " + sum);
    }

    static String[] analyseMapping(String data) {
        List<String> dataArr = Arrays.asList(data.split(" "));


        String[] output = new String[10];

        Map<Character, Integer> map = new HashMap<>();
        for (String s : dataArr) {
            if (s.length() == 2) output[1] = s;        // 1
            else if (s.length() == 3) output[7] = s;     // 7
            else if (s.length() == 4) output[4] = s;     // 4
            else if (s.length() == 7) output[8] = s;    // 8

            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
        }

        String[] mapping = new String[7];
        mapping[1] = getMapKeyWithValue(map, 6);
        mapping[4] = getMapKeyWithValue(map, 4);
        mapping[5] = getMapKeyWithValue(map, 9);

        // System.out.println(getMapKeyWithValue(map, 8));
        // System.out.println(getMapKeyWithValue(map, 7));

        // solve double eigth
        String eight = getMapKeyWithValue(map, 8);
        if (output[1].contains(Character.toString(eight.charAt(0)))) {
            mapping[2] = Character.toString(eight.charAt(0));
            mapping[0] = Character.toString(eight.charAt(1));
        } else {
            mapping[0] = Character.toString(eight.charAt(0));
            mapping[2] = Character.toString(eight.charAt(1));
        }

        //solve double seven
        String seven = getMapKeyWithValue(map, 7);
        if (output[4].contains(Character.toString(seven.charAt(0)))) {
            mapping[3] = Character.toString(seven.charAt(0));
            mapping[6] = Character.toString(seven.charAt(1));
        } else {
            mapping[6] = Character.toString(seven.charAt(0));
            mapping[3] = Character.toString(seven.charAt(1));
        }

        // System.out.println(Arrays.toString(mapping));

        /**
         *  0000
         * 1    2
         * 1    2
         *  3333
         * 4    5
         * 4    5
         *  6666
         */

        output[0] = mapping[0] + mapping[1] + mapping[2] + mapping[4] + mapping[5] + mapping[6];
        output[2] = mapping[0] + mapping[2] + mapping[3] + mapping[4] + mapping[6];
        output[3] = mapping[0] + mapping[2] + mapping[3] + mapping[5] + mapping[6];
        output[5] = mapping[0] + mapping[1] + mapping[3] + mapping[5] + mapping[6];
        output[6] = mapping[0] + mapping[1] + mapping[3] + mapping[4] + mapping[5] + mapping[6];
        output[8] = mapping[0] + mapping[1] + mapping[2] + mapping[3] + mapping[4] + mapping[5] + mapping[6];
        output[9] = mapping[0] + mapping[1] + mapping[2] + mapping[3] + mapping[5] + mapping[6];

        for (int i = 0; i < output.length; i++) {
            String current = output[i];
            output[i] = sortAlphabetically(output[i]);
        }

        // System.out.println(map);
        // System.out.println(Arrays.toString(output));

        return output;
    }

    static String sortAlphabetically(String input) {
        char[] charArr = input.toCharArray();
        Arrays.sort(charArr);

        return new String(charArr);
    }

    static String getMapKeyWithValue(Map<Character, Integer> input, int value) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : input.entrySet()) {
            if (entry.getValue() == value) {
                builder.append(entry.getKey());
            }
        }

        return builder.toString();
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
