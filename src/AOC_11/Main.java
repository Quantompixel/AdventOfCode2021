package AOC_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // simulateOctopus("res/input/AOC_11.txt");
        printOctopus(readInput("res/input/AOC_11.txt"));
        simulateOctopus(readInput("res/input/AOC_11.txt"), 195);
        // performFlash(readInput("res/input/AOC_11.txt"));
    }

    static int flashes = 0;
    static void simulateOctopus(List<List<Integer>> input, int steps) {
         // input = readInput(path);
        if (steps == 0) {
            return;
        }

        boolean performFlash = false;
        for (int row = 0; row < input.size(); row++) {
            for (int column= 0; column < input.get(row).size(); column++) {
                int current = input.get(row).get(column);
                current ++;
                if (current >= 10) {
                    performFlash = true;
                }

                input.get(row).set(column, current);
            }
        }

        // printOctopus(input);
        if (performFlash){
            input = performFlash(input);
        }
        System.out.println();
        System.out.println(flashes);
        printOctopus(input);

        simulateOctopus(input, steps-1);
    }

    static List<List<Integer>> performFlash(List<List<Integer>> input) {

        boolean hasFlashed;
        //printOctopus(input);

        do {
            hasFlashed = false;
            for (int row = 0; row < input.size(); row++) {
                for (int column = 0; column < input.get(row).size(); column++) {
                    // can flash
                    if (input.get(row).get(column) == 10) {
                        hasFlashed = true;
                        flashes++;

                        //neighbours
                        for (int y = row-1; y <= row+1; y++) {
                            if (y >= input.size() || y < 0) continue; // -> Out of bounds

                            for (int x = column-1; x <= column+1; x++) {
                                if (x >= input.get(y).size() || x < 0) continue; // -> Out of bounds

                                int neighbour = input.get(y).get(x);
                                if (neighbour < 10) {
                                    input.get(y).set(x, neighbour+1);
                                }
                                // System.out.print(input.get(y).get(x) + " ");
                            }
                            // System.out.println();
                        }
                        // System.out.println();

                        input.get(row).set(column, 11);
                    }
                }
            }
        }while (hasFlashed);


        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                int current = input.get(i).get(j);
                if (current >= 10) {
                    // flashes++;
                    input.get(i).set(j, 0);
                }
            }
        }

        return input;
    }

    static List<List<Integer>> readInput(String path) {
        List<List<Integer>> output = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                List<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char current = line.charAt(i);
                    row.add(Integer.parseInt(Character.toString(current)));
                }
                output.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    static void printOctopus(List<List<Integer>> input) {
        System.out.println();
        for (List<Integer> row : input) {
            for (Integer octopus : row) {
                // prints
                if (octopus < 10) System.out.print("  "+ octopus);
                else System.out.print(" " + octopus);
            }
            System.out.println();
        }
    }
}
