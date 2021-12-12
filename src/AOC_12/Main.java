package AOC_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(scanCaves(readInput("res/input/AOC_12.txt"), "start", new ArrayList<>()));
    }

    static String scanCaves(List<String> input, String pos, List<String> visited) {
        // System.out.println(pos);
        if (pos.charAt(0) >= 'a' && pos.charAt(0) <= 'z') {
            visited.add(pos);
        }
        if (pos.equals("end")) {
            // System.out.println();
            return pos;
        }

        for (String s : input) {
            String start = s.split("-")[0];
            String end = s.split("-")[1];

            if (start.equals(pos)) {
                if (visited.contains(end)) continue;
                return pos + ", " + scanCaves(input, end, visited);
            }
            if (end.equals(pos)) {
                if (visited.contains(start)) continue;
                return pos + ", " + scanCaves(input, start, visited);
            }
        }

        return " ";
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
