package org.days.day1.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> codedLines = readInput("src/main/java/org/days/day1/part2/input.txt");
        List<String[]> decodedLines = codedLines.stream().map(Main::decodeLine).toList();
        List<Integer> lineValues = decodedLines.stream().map(Main::calculateDecodedLineElements).toList();
        int result = lineValues.stream().reduce(0, Integer::sum);
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


    public static String[] decodeLine(String codedLine) {
        String[] decodedLineElements = new String[2];
        char[] characters = codedLine.toCharArray();
        StringBuilder actualCharacters = new StringBuilder();

        for (Character ch : characters) {
            if (Character.isDigit(ch)) {
                checkWordsInActualCharacters(decodedLineElements, actualCharacters);
                actualCharacters = new StringBuilder();
                if (decodedLineElements[0] == null) {
                    decodedLineElements[0] = ch.toString();

                } else {
                    decodedLineElements[1] = ch.toString();
                }
            } else {
                actualCharacters.append(ch.toString());
            }
        }
        if (!actualCharacters.isEmpty()) {
            checkWordsInActualCharacters(decodedLineElements, actualCharacters);
        }
        ensureTwoResultElements(decodedLineElements);
        return decodedLineElements;
    }

    private static void ensureTwoResultElements(String[] decodedLineElements) {
        for (int i = 0; i < decodedLineElements.length; i++) {
            if (decodedLineElements[1] == null) {
                decodedLineElements[1] = decodedLineElements[0];
            }
        }
    }

    private static void checkWordsInActualCharacters(String[] result, StringBuilder actualCharacters) {
        String foundWord = findWords(actualCharacters.toString(), result);
        if (foundWord != null) {
            if (result[0] == null) {
                result[0] = foundWord;
            } else {
                result[1] = foundWord;
            }
        }
    }

    public static String findWords(String actualCharacters, String[] result) {
        String foundWord = null;
        for (int i = 0; i < actualCharacters.length() - 2; i++) {
            String threeLetterWord = actualCharacters.substring(i, i + 3);
            switch (threeLetterWord) {
                case "one" -> foundWord = threeLetterWord;
                case "two" -> foundWord = threeLetterWord;
                case "six" -> foundWord = threeLetterWord;

            }
            if (i + 4 <= actualCharacters.length()) {
                String fourLetterWord = actualCharacters.substring(i, i + 4);
                switch (fourLetterWord) {
                    case "four" -> foundWord = fourLetterWord;
                    case "five" -> foundWord = fourLetterWord;
                    case "nine" -> foundWord = fourLetterWord;
                }
            }
            if (i + 5 <= actualCharacters.length()) {
                String fiveLetterWord = actualCharacters.substring(i, i + 5);
                switch (fiveLetterWord) {
                    case "seven" -> foundWord = fiveLetterWord;
                    case "eight" -> foundWord = fiveLetterWord;
                    case "three" -> foundWord = fiveLetterWord;
                }
            }
            if (foundWord != null) {
                if (result[0] == null) {
                    result[0] = foundWord;
                } else {
                    result[1] = foundWord;
                }
            }
        }
        return foundWord;
    }

    public static int calculateDecodedLineElements(String[] decodedLineElements) {
        int value = 0;
        for (int i = 0; i < decodedLineElements.length; i++) {
            int element = 0;
            if (decodedLineElements[i].length() == 1) {
                element = Integer.parseInt(decodedLineElements[i]);
            } else {
                switch (decodedLineElements[i]) {
                    case "one":
                        element = 1;
                        break;
                    case "two":
                        element = 2;
                        break;
                    case "three":
                        element = 3;
                        break;
                    case "four":
                        element = 4;
                        break;
                    case "five":
                        element = 5;
                        break;
                    case "six":
                        element = 6;
                        break;
                    case "seven":
                        element = 7;
                        break;
                    case "eight":
                        element = 8;
                        break;
                    case "nine":
                        element = 9;
                        break;
                }
            }
            if (i == 0) {
                value = element * 10;
            } else {
                value += element;
            }
        }
        return value;
    }

}
