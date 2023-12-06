package org.days.day6.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readInput("src/main/java/org/days/day6/part2/input.txt");
        List<Long> raceTimes = extractData(lines, 0);
        List<Long> raceDistances = extractData(lines, 1);
        List<Integer> numberOfOptionsToWinPerRaces = calculateNumberOfOptionsToWin(raceTimes, raceDistances);
        int result = numberOfOptionsToWinPerRaces.stream().reduce(1, (a, b) -> a * b);
        System.out.println(result);

    }

    private static List<Long> extractData(List<String> lines, int index) {
        return Arrays.stream(lines.get(index).split(" "))
                .skip(1)
                .filter(s -> !s.trim().isEmpty())
                .mapToLong(Long::parseLong)
                .boxed()
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

    private static List<Integer> calculateNumberOfOptionsToWin(List<Long> raceTimes, List<Long> raceDistances) {
        List<Integer> numberOfOptionsToWinPerRace = new ArrayList<>();
        for (int i = 0; i < raceTimes.size(); i++) {
            int numberOfOptionsToWin = calculateOptions(raceTimes.get(i), raceDistances.get(i));
            numberOfOptionsToWinPerRace.add(numberOfOptionsToWin);
        }
        return numberOfOptionsToWinPerRace;
    }

    private static int calculateOptions(long raceTime, long raceDistance) {
        int numberOfOptions = 0;
        for (int i = 0; i <= raceTime; i++) {
            int speed = 0;
            long timeLeft = raceTime;
            speed = speed + i;
            timeLeft = raceTime - i;
            if (speed * timeLeft > raceDistance) {
                numberOfOptions++;
            }
        }
        return numberOfOptions;
    }
}
