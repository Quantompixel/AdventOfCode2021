package AOC_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        scanCaves(readInput("res/input/AOC_12.txt"), "start", new ArrayList<>(),"" ,new ArrayList<>());
        System.out.println(counter);
        System.out.println(onceVisited);
    }

    static int counter = 0;
    static List<String> onceVisited = new ArrayList<>();

    static void scanCaves(List<String> input, String pos, List<String> visited, String visitedOnce, List<String> path) {
        if (pos.equals("end")) {
            // System.out.println();
            System.out.println(path + ", end");
            counter++;
            return;
        }

        if (pos.charAt(0) >= 'a' && pos.charAt(0) <= 'z') {
            if (pos.equals("start")) {
                visited.add(pos);
            } else if (visitedOnce.isBlank()) {
                onceVisited.add(pos);
                visitedOnce = pos;
            } else {
                visited.add(pos);
            }
        }

        for (String s : input) {
            String start = s.split("-")[0];
            String end = s.split("-")[1];

            List<String> pfad = new ArrayList<>(path);
            pfad.add(pos);
            if (start.equals(pos)) {
                if (visited.contains(end)) continue;
                scanCaves(input, end, new ArrayList<>(visited), visitedOnce,pfad);
            }
            if (end.equals(pos)) {
                if (visited.contains(start)) continue;
                scanCaves(input, start, new ArrayList<>(visited), visitedOnce,pfad);
            }
        }
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
