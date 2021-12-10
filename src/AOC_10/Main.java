package AOC_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        checkSyntax("res/input/AOC_10.txt");
    }

    static void checkSyntax(String path) {
        List<String> input = readInput(path);
        List<Character> failures = new ArrayList<>();
        int output = 0;
        List<Long> totalScores = new ArrayList<>();

        for (String s : input) {
            List<Character> opened = new ArrayList<>();
            boolean hasFailed = false;
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
                    //nicht incomplete
                    hasFailed = true;
                    break;
                }
            }
            if (!hasFailed) {
                //System.out.println(reverseList(opened));
                totalScores.add(calculateScore(reverseList(opened)));
                System.out.println(opened);
                System.out.println(calculateScore(reverseList(opened)));
            }
        }
        totalScores.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o1,o2);
            }
        });
        System.out.println(totalScores);
        System.out.println(totalScores.get(totalScores.size()/2));
    }

    static long calculateScore(List<Character> list) {
        long totalScore = 0;
        for (Character character : list) {
            totalScore *= 5;
            switch (character) {
                case '(':
                    totalScore += 1;
                    break;
                case '[':
                    totalScore += 2;
                    break;
                case '{':
                    totalScore += 3;
                    break;
                case '<':
                    totalScore += 4;
                    break;
            }
        }

        return totalScore;
    }

    static List<Character> reverseList(List<Character> list) {
        List<Character> output = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            output.add(list.get(i));
        }
        return output;
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
