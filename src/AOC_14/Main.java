package AOC_14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    }

    static List<String> rules(String path) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.subList(2,lines.size());
    }
}
