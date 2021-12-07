package AOC_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        calculateCrabPoses(readInput("res/input/AOC_7.txt"));
        int fuel = 0;
        for (int y = 1; y <= 2; y++) {
            fuel+=y;
        }

        System.out.println(fuel);
    }

    static void calculateCrabPoses(List<Integer> input) {
        //input.sort((o1, o2) -> o2 - o1);

        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            int sum = 0;
            for (Integer integer : input) {
                int distance = Math.abs(integer - i);
                // sum += Math.abs(integer - i);
                int fuel = 0;
                for (int y = 1; y <= distance; y++) {
                    fuel+=y;
                }
                System.out.println(fuel);
                sum += fuel;
            }
            output.add(sum);
        }
        output.sort(Comparator.comparingInt(o -> o));
        System.out.println(output.get(0));

        /*int sum = 0;
        for (Integer integer : input) {
            sum += Math.abs(integer - 2);
        }
        System.out.println(sum);

         */

        System.out.println(output);
    }

    static List<Integer> readInput(String path) {
        List<Integer> list = new ArrayList<>();
        try {
            String[] split = Files.readString(Paths.get(path)).split(",");
            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
