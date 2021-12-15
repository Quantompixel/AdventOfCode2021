package AOC_15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        init("res/input/AOC_15.txt");
    }

    // invert the position to show that is was already visited: +3 -> -3
    static int[][] map;
    static int[][] vertexes;

    static void djikstra (int[][] input, List<Integer> path) {
        /**
         * visited[], unvisited[]
         *
         * Vertex | Shortest distance from root | Previous Vertex
         *    0                 0
         *    1                 infinit
         *    2                 ....
         *    3
         *    ...
         *
         * Die Vertext besuchen, die die kleinste Distanz zum start hat -> also 0 selbst
         * Alle unbesuchten Nachbarn anschauen
         * Distanz zur startveterx kalkulieren
         * Wenn die Distanz zu den nachbarn kleiner ist als die gespeicherte Distanz wird die Tabelle geupdated
         * Und die Vertex wird eingetragen über die die Nachbarn erreicht wurden
         * Die aktuelle Vertex wird als besucht abgspeichert
         *
         * Dann springen wir zur Vertex mit der kleinsten Distanz zum start
         * Alle unbesuchten nachbarn
         * ....
         *
         * int[][]
         * Vertex 0: [kuerzester weg zu root],[Vorgaenger]
         *
         *
         * map[][]
         * 0 1 2 3 ...
         * 7 8 9 ...
         */
        
        
    }

    static void init(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        map = new int[lines.size()][lines.get(0).length()];
        vertexes = new int[lines.size()][lines.get(0).length()];

        int counter = 0;
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(row).length(); column++) {
                map[row][column] = Integer.parseInt(Character.toString(lines.get(row).charAt(column)));
                vertexes[row][column] = counter;
                counter ++;
            }
        }

        for (int[] ints : vertexes) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
