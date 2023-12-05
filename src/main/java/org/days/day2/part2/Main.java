package org.days.day2.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readInput("src/main/java/org/days/day2/part2/input.txt");
        List<List<String>> reformattedLines = reformatLines(lines);
        List<Integer> setPowersOfGames = countSetPowersOfGames(reformattedLines);
        int result = setPowersOfGames.stream().reduce(0, Integer::sum);
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
                        .skip(2)
                        .toList())
                .toList();
    }

    public static List<Integer> countSetPowersOfGames(List<List<String>> games) {
        List<Integer> powerOfSets = new ArrayList<>();
        for (List<String> game : games) {
            int powerOfASet = countPowerOfASet(game);
            powerOfSets.add(powerOfASet);
        }
        return powerOfSets;
    }

    private static int countPowerOfASet(List<String> game) {
        int currentCount = 0;
        int highestGreen = 0;
        int highestRed = 0;
        int highestBlue = 0;
        for (int i = 0; i < game.size(); i++) {
            if (i % 2  == 0) {
                currentCount = Integer.parseInt(game.get(i));
            } else {
                switch (game.get(i)) {
                    case "red":
                        if (currentCount > highestRed) {
                            highestRed = currentCount;
                        }
                        currentCount = 0;
                        break;
                    case "green":
                        if (currentCount > highestGreen) {
                            highestGreen = currentCount;
                        }
                        currentCount = 0;
                        break;
                    case "blue":
                       if (currentCount > highestBlue) {
                           highestBlue = currentCount;
                       }
                        currentCount = 0;
                        break;
                }
            }
        }
        return highestGreen * highestBlue * highestRed;
    }
}
