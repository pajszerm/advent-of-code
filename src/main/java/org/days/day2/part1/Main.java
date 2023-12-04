package org.days.day2.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readInput("src/main/java/org/days/day2/part1/input.txt");
        List<List<String>> reformattedLines = reformatLines(lines);
        List<Integer> possibleGameIndexes = countPossibleGameIndexes(reformattedLines);
        int result = possibleGameIndexes.stream().reduce(0, Integer::sum);
        System.out.println(result);
    }

    public static List<String> readInput(String inputTxt) {
        Path input = Path.of(inputTxt);

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(input)) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static List<List<String>> reformatLines(List<String> lines) {
        return lines.stream()
                .map(element -> Arrays.stream(element.replaceAll("[,;:]", "").split(" "))
                        .skip(1)
                        .toList())
                .toList();
    }

    public static List<Integer> countPossibleGameIndexes(List<List<String>> games) {
        List<Integer> possibleGames = new ArrayList<>();
        for (List<String> game : games) {
            int gameIndex = Integer.parseInt(game.get(0));
            boolean possibleGame = checkIfGameIsPossible(game);
            if (possibleGame) {
                    possibleGames.add(gameIndex);
                }
            }
        return possibleGames;
    }

    private static boolean checkIfGameIsPossible(List<String> game) {
        int currentCount = 0;
        boolean isGamePossible = true;
        for (int i = 1; i < game.size(); i++) {
            if (i % 2  != 0) {
                currentCount = Integer.parseInt(game.get(i));
            } else {
                switch (game.get(i)) {
                    case "red":
                        if (currentCount > 12) {
                            return false;
                        }
                        currentCount = 0;
                    case "green":
                        if (currentCount > 13) {
                            return false;
                        }
                        currentCount = 0;
                    case "blue":
                        if (currentCount > 14) {
                            return false;
                        }
                        currentCount = 0;
                }
            }
        }
        return isGamePossible;
    }
}






