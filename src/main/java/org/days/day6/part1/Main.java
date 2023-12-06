package org.days.day6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readInput("src/main/java/org/days/day6/part1/input.txt");
        List<Integer> raceTimes = extractData(lines, 0);
        List<Integer> raceDistances = extractData(lines, 1);
        List<Integer> numberOfOptionsToWinPerRaces = calculateNumberOfOptionsToWin(raceTimes, raceDistances);
        int result = numberOfOptionsToWinPerRaces.stream().reduce(1, (a, b) -> a * b);
        System.out.println(result);

    }

    private static List<Integer> extractData(List<String> lines, int index) {
        return Arrays.stream(lines.get(index).split(" "))
                .skip(1)
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .toList();
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

    private static List<Integer> calculateNumberOfOptionsToWin(List<Integer> raceTimes, List<Integer> raceDistances) {
        List<Integer> numberOfOptionsToWinPerRace = new ArrayList<>();
        for (int i = 0; i < raceTimes.size(); i++) {
            int numberOfOptionsToWin = calculateOptions(raceTimes.get(i), raceDistances.get(i));
            numberOfOptionsToWinPerRace.add(numberOfOptionsToWin);
        }
        return numberOfOptionsToWinPerRace;
    }

    private static int calculateOptions(Integer raceTime, Integer raceDistance) {
        int numberOfOptions = 0;
        for (int i = 0; i <= raceTime; i++) {
            int speed = 0;
            int timeLeft = raceTime;
            speed = speed + i;
            timeLeft = raceTime - i;
            if (speed * timeLeft > raceDistance) {
                numberOfOptions++;
            }
        }
        return numberOfOptions;
    }
}
