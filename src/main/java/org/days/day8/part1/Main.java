package org.days.day8.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readInput("src/main/java/org/days/day8/part1/input.txt");
        List<String> reformattedLines = reformatLines(lines);
        String[] directionOrder = reformattedLines.remove(0).split("");
        Map<String, String[]> directionMap = createDirectionMap(reformattedLines);
        int result = countStepsToReachZZZ(directionOrder, directionMap);
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

    private static Map<String, String[]> createDirectionMap(List<String> reformattedLines) {
        return reformattedLines.stream()
                .map(line -> line.split("\\s+"))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> Arrays.copyOfRange(parts, 1, parts.length)
                ));
    }

    private static List<String> reformatLines(List<String> lines) {
        return lines.stream().map(e -> e.replaceAll("[,=\\(\\)]", ""))
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
    }

    private static int countStepsToReachZZZ(String[] directionOrder, Map<String, String[]> directionMap) {
        List<String> nodeList = new ArrayList<>(directionMap.keySet());
        int steps = 0;
        int directionIndex = 0;
        String currentNode = nodeList.get(0);
        while (!currentNode.equals("ZZZ")) {
            for (int i = directionIndex; i < directionOrder.length; i++) {
                currentNode = directionOrder[i].equals("R") ? directionMap.get(currentNode)[1] : directionMap.get(currentNode)[0];
                steps++;
                directionIndex++;
                directionIndex = checkDirectionOrderIndex(directionOrder, directionIndex);
                if (currentNode.equals("ZZZ")) {
                    break;
                }
            }
        }
        return steps;
    }

    private static int checkDirectionOrderIndex(String[] directionOrder, int directionIndex) {
        if (directionIndex == directionOrder.length) {
            directionIndex = 0;
        }
        return directionIndex;
    }

}
