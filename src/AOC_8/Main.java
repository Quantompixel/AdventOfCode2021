package AOC_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        getUniqueNumbers("res/input/AOC_8.txt");
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
