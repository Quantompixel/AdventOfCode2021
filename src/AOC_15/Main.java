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
        djikstra();
        djikstra();
    }

    // invert the position to show that is was already visited: +3 -> -3
    static int[][] map;
    static int[][] vertexes;
    static List<Integer> unvisited = new ArrayList<>();
    static List<Integer> visited = new ArrayList<>();

    /**
     * <pre>visited[], unvisited[]</pre>
     *
     * <pre>
     *    Vertex | Shortest distance from root | Previous Vertex
     *    0                 0                           0
     *    1                 infinit                    -1
     *    2                 ....
     *    3
     *    ...
     * </pre>
     *
     * <pre>1.    Die Vertext besuchen, die die kleinste Distanz zum start hat -> also 0 selbst</pre>
     * <pre>2.    Alle unbesuchten Nachbarn anschauen</pre>
     * <pre>3.    Distanz zur startveterx kalkulieren</pre>
     * <pre>4.    Wenn die Distanz zu den nachbarn kleiner ist als die gespeicherte Distanz wird die Tabelle geupdated</pre>
     * <pre>5.    Und die Vertex wird eingetragen über die die Nachbarn erreicht wurden</pre>
     * <pre>6.    Die aktuelle Vertex wird als besucht abgspeichert</pre>
     *
     * <pre>Dann springen wir zur Vertex mit der kleinsten Distanz zum start</pre>
     * <pre>Alle unbesuchten nachbarn</pre>
     * ....
     *
     * <pre>
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
        int distance = vertexes[current][1];
        System.out.println("\n closest Node: " + closest + "\n");
        //Nachbarn
        int height = map.length;
        int width = map[0].length;
        int row = current/height;
        int column = current%width;

        for (int y = column-1; y <= column+1; y++) {
            if (y < 0 || y >= height) continue;
            for (int x = row-1; x <= row+1; x++) {
                if (x < 0 || x >= width) continue;
                if (y == row && x == column) continue;
                System.out.println(convertPosToVertexID(y, x));

                int neighbor = convertPosToVertexID(y, x);
                if (unvisited.contains(neighbor)) {
                    // ubesuchter Nachbar
                    int[] neighborEntry = vertexes[neighbor];
                    int neigbourDistance = map[y][x];

                    int distanceToNeighbour = distance + Math.abs(current-neigbourDistance);

                    if (distanceToNeighbour < neighborEntry[0]) {
                        vertexes[neighbor][0] = distanceToNeighbour;
                        vertexes[neighbor][1] = current;
                    }
                }
            }
        }

        System.out.println();
        for (int[] vertex : vertexes) {
            System.out.println(Arrays.toString(vertex));
        }

        //aktuelle Vertex aus unbesuchten entfernen
        unvisited.remove(current);
    }

    static int distanceToStartVertex(int vertex) {
        int i = 0;
        return i;
    }

    static int convertPosToVertexID(int row, int column) {
        int height = map.length;
         int width = map[0].length;

        return row*height+column;
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

        for (int[] ints : map) {
            //System.out.println(Arrays.toString(ints));
        }
        for (int[] ints : vertexes) {
             System.out.println(Arrays.toString(ints));
        }
        System.out.println(unvisited);

        // System.out.println(unvisited);
    }
}
