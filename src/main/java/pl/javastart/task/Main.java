package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final int STRING_LENGTH = 10;

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        String dateEntered = collectingDate(scanner);
        showingResult(dateEntered);
    }

    private static void showingResult(String dateEntered) {
        List<String> patterns = Arrays.asList("yyyy-MM-dd HH-mm-ss", "yyyy-MM-dd HH:mm:ss", "dd.MM.yyyy HH:mm:ss");
        System.out.println(dateEntered.length());
        if (dateEntered.length() == STRING_LENGTH) {
            dateEntered += " 00:00:00";
        }
        boolean added = false;
        for (String pat : patterns) {
            try {
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern(pat);
                LocalDateTime localDateTime = LocalDateTime.parse(dateEntered, pattern);
                LocalDateTime londonTime = localDateTime.minusHours(1);
                LocalDateTime losAngelesTime = localDateTime.minusHours(9);
                LocalDateTime sydneyTime = localDateTime.plusHours(10);
                System.out.println("Czas lokalny: " + convertToString(localDateTime) + ":00");
                System.out.println("UTC: " + convertToString(londonTime) + ":00");
                System.out.println("Londyn: " + convertToString(londonTime) + ":00");
                System.out.println("Los Angeles: " + convertToString(losAngelesTime) + ":00");
                System.out.println("Sydney: " + convertToString(sydneyTime) + ":00");
                added = true;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        if (!added) {
            System.out.println("Niepoprawny format daty.");
        }
    }

    private static String convertToString(LocalDateTime localDateTime) {
        String localD = localDateTime.toString();
        return localD.replace('T', ' ');
    }

    private static String collectingDate(Scanner scanner) {
        System.out.println("Podaj datę:");
        return scanner.nextLine();
    }

}
