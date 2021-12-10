package AOC_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        checkSyntax("res/input/AOC_10.txt");
    }

    static void checkSyntax(String path) {
        List<String> input = readInput(path);
        List<Character> failures = new ArrayList<>();
        int output = 0;

        for (String s : input) {
            List<Character> opened = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                // System.out.println(opened);
                if (current == '(' || current == '[' || current == '{' || current == '<') {
                    opened.add(current);
                } else {
                    char last = opened.get(opened.size() - 1);
                    switch (current) {
                        case ')':
                            if (last == '('){ opened.remove(opened.size()-1);}
                            else {
                                // System.out.println("fehler: " + current);
                                output += 3;
                                failures.add(current);
                                break;
                            }

                            continue;
                        case ']':
                            if (last == '[') opened.remove(opened.size()-1);
                            else {
                                // System.out.println("fehler: " + current);
                                output += 57;
                                failures.add(current);
                                break;
                            }
                            continue;
                        case '}':
                            if (last == '{') opened.remove(opened.size()-1);
                            else {
                                // System.out.println("fehler: " + current);
                                output += 1197;
                                failures.add(current);
                                break;
                            }
                            continue;
                        case '>':
                            if (last == '<') opened.remove(opened.size()-1);
                            else {
                                // System.out.println("fehler: " + current);
                                output += 25137;
                                failures.add(current);
                                break;
                            }
                            continue;
                    }
                    // System.out.println(current);
                    break;
                }
            }
        }
        

        System.out.println(output);
    }

    static List<String> readInput(String path) {
        List<String> list = new ArrayList<>();

        try {
            list = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
