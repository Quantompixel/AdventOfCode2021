package AOC_14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // System.out.println(rules("res/input/AOC_14.txt"));
        // System.out.println(getInput("res/input/AOC_14.txt"));
        // System.out.println(polymer(getInput("res/input/AOC_14.txt"),"res/input/AOC_14.txt"));
        createPolymer("res/input/AOC_14.txt", 10);
    }

    static void createPolymer(String path, int iterations) {
        String input = getInput(path);

        for (int i = 0; i < iterations; i++) {
            input = polymer(input, path);
        }

        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            int value = count.getOrDefault(input.charAt(i), 0) + 1;
            count.put(input.charAt(i), value);
        }
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() < min) min = entry.getValue();
            if (entry.getValue() > max) max = entry.getValue();
        }

        System.out.println(count);
        System.out.println(max - min);
    }

    static String polymer(String input,String path) {
        List<String> rules = getRules(path);
        StringBuilder output = new StringBuilder();
        output.append(input.charAt(0));

        for (int i = 0; i < input.length() - 1; i++) {
            // System.out.println(input.substring(i, i + 2));
            String str = input.substring(i, i + 2);
            String get = "";

            for (String rule : rules) {
                if (rule.contains(str)) {
                    String[] split = rule.split("[ ]->[ ]");
                    get = split[1].trim();
                }
            }

            output.append(get).append(str.charAt(1));
        }

        return output.toString();
    }

    static String getInput(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    static List<String> getRules(String path) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.subList(2,lines.size());
    }
}
