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

        for (int i = 0; i < vertexes.length; i++) {
            djikstra();
        }

        System.out.println();

        System.out.println("ID    Dist  Prev");
        for (int i = 0; i < vertexes.length; i++) {
            String infinity = "\u221E";
            String output = '[' + String.format("% 3d", vertexes[i][0]) + ']' + " " + '[' + String.format("% 3d", vertexes[i][1]) + ']';
            output = output.replace(" 2147483647", "  " + infinity);
            System.out.println(String.format("%02d", i) + "  : " + output);
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
        int closest = 0;
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i][0] < min && unvisited.contains(i)) {
                min = vertexes[i][0];
                closest = i;
            }
        }
        int current = closest;

        Object o = current;
        //aktuelle Vertex aus unbesuchten entfernen
        unvisited.remove(o);

        int distance = vertexes[current][0];

        //Nachbarn
        int height = map.length;
        int width = map[0].length;
        int row = current / height;
        int column = current % width;

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

            int neighbor = convertPosToVertexID(y, x);
            if (unvisited.contains(neighbor)) {
                // ubesuchter Nachbar
                int[] neighborEntry = vertexes[neighbor];
                int neighborValue = map[y][x];

                if (neighborValue + distance < neighborEntry[0]) {
                    vertexes[neighbor][0] = neighborValue + distance;
                    vertexes[neighbor][1] = current;
                }
            }
        }

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
        vertexes = new int[lines.size() * lines.get(0).length()][2];

        int counter = 0;
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(row).length(); column++) {
                map[row][column] = Integer.parseInt(Character.toString(lines.get(row).charAt(column)));
                vertexes[counter][0] = Integer.MAX_VALUE;
                vertexes[counter][1] = -1;
                counter++;
            }
        }

        for (int i = 0; i < counter; i++) {
            unvisited.add(i);
        }

        vertexes[0][0] = 0;
        vertexes[0][1] = 0;

        for (int[] ints : vertexes) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(unvisited);

    }
}
