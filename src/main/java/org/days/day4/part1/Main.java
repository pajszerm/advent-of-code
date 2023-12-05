package org.days.day4.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> lines = readInput("src/main/java/org/days/day4/part1/input.txt");
        List<List<String>> reformattedLines = reformatLines(lines);
        List<Integer> pointsPerGames = countPointsPerGames(reformattedLines);
        int result = pointsPerGames.stream().reduce(0, Integer::sum);
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
                .map(element -> Arrays.stream(element.replaceAll("[:,|]", "").split(" "))
                        .filter(e -> !e.isEmpty())
                        .skip(2)
                        .toList())
                .toList();
    }

    public static List<Integer> countPointsPerGames(List<List<String>> games) {
        List<Integer> pointsPerGames = new ArrayList<>();
        for (List<String> game : games) {
            int gamePoints = countGamePoints(game);
            pointsPerGames.add(gamePoints);
        }
        return pointsPerGames;
    }

    private static int countGamePoints(List<String> game) {
        List<String> winningNumbers = game.subList(0, 10);
        List<String> myNumbers = game.subList(10, game.size());
        List<String> differences = myNumbers.stream()
                .filter(element -> !winningNumbers.contains(element))
                .toList();
        return countPointsByDifference(differences.size());
    }

    private static int countPointsByDifference(int size) {
        int numberOfWinningNumbers = 25 - size;
        int points = 0;
        for (int i = 0; i < numberOfWinningNumbers; i++) {
            if (i == 0) {
                points++;
            } else {
                points = points * 2;
            }

        }
        return points;
    }
}
