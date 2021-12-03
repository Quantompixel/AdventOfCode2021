package AOC_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        calculatePos(fileToList(Paths.get("res/input/AOC_2.txt")));
    }

    public static void calculatePos(List<String> lines) {
        int pos = 0;
        int depth = 0;
        int aim = 0;


        for (String line : lines) {
            String[] split = line.split(" ");

            String direction = split[0];
            int amount = Integer.parseInt(split[1]);

            switch (direction) {
                case "forward" -> {
                    pos += amount;
                    depth += aim * amount;
                }
                case "up" -> aim -= amount;
                case "down" -> aim += amount;
            }
        }

        // System.out.println(horizontalPos + " : " + depth);
        System.out.println(pos * depth);
    }

    public static List<String> fileToList(Path file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
