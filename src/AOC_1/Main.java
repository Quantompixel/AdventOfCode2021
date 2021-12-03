package AOC_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        partTwo();
    }

    public static void partTwo() {
        List<Integer> input = fileToIntList(Paths.get("res/input/AOC_1.txt"));

        int counter = 0;
        int last = -1;
        for (int j = 0; j < input.size() - 3; j++) {
            int current = 0;
            for (int i = 0; i < 3; i++) {
                current += input.get(j + i);
            }
            if (current == -1) {
                continue;
            }

            if (current > last) {
                counter++;
            }

            last = current;
        }

        System.out.println(counter);
    }

    public static void partOne(){
        Path input = Paths.get("res/input/AOC_1.txt");
        List<String> lines = new ArrayList<>();
        int counter = 0;
        int last = Integer.parseInt(lines.get(0));
        for (String line : lines) {
            int current = Integer.parseInt(line);
            if (current > last) {
                counter++;
            }
            last = current;
        }

        System.out.println(counter);
    }

    public static List<Integer> fileToIntList(Path file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> nums = new ArrayList<>();
        for (String line : lines) {
            nums.add(Integer.parseInt(line));
        }

        return nums;
    }
}
