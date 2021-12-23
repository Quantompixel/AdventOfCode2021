package AOC_20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
