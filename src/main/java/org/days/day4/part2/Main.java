package org.days.day4.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> lines = readInput("src/main/java/org/days/day4/part2/input.txt");
        List<List<String>> reformattedLines = reformatLines(lines);
        int numberOfGames = countGames(reformattedLines);
        System.out.println(numberOfGames);
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
                        .skip(1)
                        .toList())
                .toList();
    }

    public static int countGames(List<List<String>> games) {
        Map<Integer, Integer> gameCounter = createGameCounter(games);
        for (List<String> game : games) {
            int numberOfWinningNumbers = countWinningNumbers(game);
            int currentGameIndex = Integer.parseInt(game.get(0));
            modifyGameCounter(currentGameIndex, gameCounter, numberOfWinningNumbers);
        }
        return countAllGames(gameCounter);
    }


    private static int countAllGames(Map<Integer, Integer> gameCounter) {
        int numberOfGames = 0;
        for (Integer value : gameCounter.values()) {
            numberOfGames += value;
        }
        return numberOfGames;
    }

    private static Map<Integer, Integer> createGameCounter(List<List<String>> games) {
        Map<Integer, Integer> gameCounter = new HashMap<>();
        for (int i = 1; i <= games.size(); i++) {
            gameCounter.put(i, 1);
        }
        return gameCounter;
    }

    private static void modifyGameCounter(int currentGameIndex, Map<Integer, Integer> gameCounter, int numberOfWinningNumbers) {
        for (int i = 1; i <= gameCounter.get(currentGameIndex); i++) {
            for (int j = 1; j <= numberOfWinningNumbers; j++) {
                gameCounter.put(currentGameIndex + j, gameCounter.get(currentGameIndex + j) + 1);
            }
        }
    }

    private static int countWinningNumbers(List<String> game) {
        List<String> winningNumbers = game.subList(1, 11);
        List<String> myNumbers = game.subList(11, game.size());
        List<String> differences = myNumbers.stream()
                .filter(element -> !winningNumbers.contains(element))
                .toList();

        return 25 - differences.size();
    }
}
