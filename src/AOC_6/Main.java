package AOC_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //simulateFish(256, readFile("res/input/AOC_6.txt"));
        simulateFishImproved(256, readFile("res/input/AOC_6.txt"));
    }

    static void simulateFishImproved(int days, List<Integer> input) {
        // Lauflängencodieren
        // [0] 3 -> 3 fish with 0
        long[] fish = new long[9];

        for (Integer integer : input) {
            fish[integer]++;
        }

        System.out.println(" " + 0 + "  " + 1 + "  " + 2 + "  " + 3 + "  " + 4 + "  " + 5 + "  " + 6 + "  " + 7 + "  " + 8 + " ");
        System.out.println(Arrays.toString(fish));

        for (int i = 0; i < days; i++) {
            long index0 = fish[0];
            long[] neu = new long[fish.length];
            for (int j = 0; j < fish.length-1; j++) {
                neu[j] = fish[j+1];
            }
            neu[8] = index0;
            neu[6] += index0;
            fish = neu.clone();
            System.out.println(Arrays.toString(fish));
        }

        long sum = 0;
        for (long i : fish) {
            sum+=i;
        }
        System.out.println(sum);

    }

    static void simulateFish(int days, List<Integer> input) {
        //System.out.println(days + ":" + input);
        if (days <= 0) {
            System.out.println(input.size());
            return;
        }

        int originalSize = input.size();

        for (int i = 0; i < originalSize; i++) {
            int current = input.get(i);

            if (current == 0) {
                input.set(i, 6);
                input.add(8);
                continue;
            }
            input.set(i, current - 1);
        }

        simulateFish(days - 1, input);
    }

    static List<Integer> readFile(String path) {
        List<Integer> output = new ArrayList<>();
        try {
            String[] string = Files.readString(Paths.get(path)).split(",");
            for (String s : string) {
                output.add(Integer.parseInt(s));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
