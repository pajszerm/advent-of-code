package org.days.day1.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> codedLines = readInput("src/main/java/org/days/day1/part1/input.txt");
        List<String> numberStrings = filterCodedLinesForNumbers(codedLines);
        int sum = sumTwoDigitNumbers(numberStrings);
        System.out.println(sum);
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


    public static List<String> filterCodedLinesForNumbers(List<String> codedLines) {
//        List<String> numbersList = new ArrayList<>();
//        for (String codedLine : codedLines) {
//            List<Character> numbers = new ArrayList<>();
//            char[] characters = codedLine.toCharArray();
//            for (char character : characters) {
//                if (Character.isDigit(character)) {
//                    numbers.add(character);
//                }
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            for (Character ch : numbers) {
//                stringBuilder.append(ch);
//            }
//            numbersList.add(stringBuilder.toString());
//        }
//        return numbersList;
        List<String> numberList = codedLines.stream()
                .map(str -> str.chars()
                        .filter(Character::isDigit)
                        .mapToObj(ch -> String.valueOf((char) ch))
                        .collect(Collectors.joining()))
                .toList();
        return numberList;
    }

    public static int sumTwoDigitNumbers(List<String> numberStrings) {
//        for (String numberString : numberStrings) {
//            if (numberString.length() == 1) {
//                int digit = Character.getNumericValue(numberString.charAt(0));
//                int twoDigitNumber = digit * 10 + digit;
//                sum += twoDigitNumber;
//            }
//            if (numberString.length() >= 2) {
//                int firstDigit = Character.getNumericValue(numberString.charAt(0));
//                int lastDigit = Character.getNumericValue(numberString.charAt(numberString.length() -1));
//                int twoDigitNumber = firstDigit *10 + lastDigit;
//                sum += twoDigitNumber;
//            }
//        }
//        return sum;
        int sum =  numberStrings.stream()
                .mapToInt(str -> {
                    if (str.length() == 1) {
                        int digit = Character.getNumericValue(str.charAt(0));
                        return digit * 10 + digit;
                    }
                    if (str.length() >= 2) {
                        int firstDigit = Character.getNumericValue(str.charAt(0));
                        int lastDigit = Character.getNumericValue(str.charAt(str.length() - 1));
                        return firstDigit * 10 + lastDigit;
                    }
                    return 0; // Default value if the string is empty
                })
                .sum();
        return sum;
    }
}
