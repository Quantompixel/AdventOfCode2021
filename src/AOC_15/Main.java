package AOC_15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        init("res/input/AOC_15.txt");

        for (int i = 0; i < vertexes.length * vertexes[0].length; i++) {
            djikstra();
        }

        System.out.println();

        for (int[] vertex : vertexes) {
            System.out.println(Arrays.toString(vertex));
        }
    }

    static int[][] map;
    static int[][] vertexes;
    static List<Integer> unvisited = new ArrayList<>();

    /**
     * <pre>
     * visited[], unvisited[]
     *
     *    Vertex | Shortest distance from root | Previous Vertex
     *    0                 0                           0
     *    1                 infinit                    -1
     *    2                 ....
     *    3
     *    ...
     *
     * 1.    Die Vertext besuchen, die die kleinste Distanz zum start hat -> also 0 selbst
     * 2.    Alle unbesuchten Nachbarn anschauen
     * 3.    Distanz zur startveterx kalkulieren
     * 4.    Wenn die Distanz zu den nachbarn kleiner ist als die gespeicherte Distanz wird die Tabelle geupdated
     * 5.    Und die Vertex wird eingetragen über die die Nachbarn erreicht wurden
     * 6.    Die aktuelle Vertex wird als besucht abgspeichert
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
     * </pre>
     */
    static void djikstra() {
        int min = Integer.MAX_VALUE;
        int row = 0;
        int column = 0;

        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes[i].length; j++) {
                int temp = vertexes[i][j];
                if (temp >= 0 && temp < min) {
                    min = vertexes[i][j];
                    row = i;
                    column = j;
                }
            }
        }

        // System.out.println("\n chosen node: " + vertexes[row][column]);
        // System.out.println("neigbors:");

        int distance = vertexes[row][column];

        // Als Besucht markieren: +5 -> -5
        if (vertexes[row][column] == 0) vertexes[row][column] = -1;
        else vertexes[row][column] = vertexes[row][column] * -1;

        //Nachbarn
        int height = map.length;
        int width = map[0].length;

        int[][] neighbors = new int[][]{
                //[column, row]
                new int[]{0, -1},
                new int[]{+1, 0},
                new int[]{0, +1},
                new int[]{-1, 0},
        };

        for (int i = 0; i < neighbors.length; i++) {
            int x = column + neighbors[i][0];
            int y = row + neighbors[i][1];

            if (x < 0 || x >= width) continue;
            if (y < 0 || y >= height) continue;

            int neighbor = vertexes[y][x];
            if (neighbor > 0) {
                // ubesuchter Nachbar
                int neighborValue = map[y][x];
                // System.out.println(neighborValue);

                if (neighborValue + distance < vertexes[y][x]) {
                    vertexes[y][x] = neighborValue + distance;
                }
            }
        }

        // for (int[] vertex : vertexes) {
            // System.out.println(Arrays.toString(vertex));
        // }

    }

    static int convertPosToVertexID(int row, int column) {
        int height = map.length;
        int width = map[0].length;

        return row * height + column;
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

        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(row).length(); column++) {
                map[row][column] = Integer.parseInt(Character.toString(lines.get(row).charAt(column)));
                vertexes[row][column] = Integer.MAX_VALUE;
            }
        }

        vertexes[0][0] = 0;

        for (int[] ints : vertexes) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println();
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }

    }
}
