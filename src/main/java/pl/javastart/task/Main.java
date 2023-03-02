package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final int DATE_LENGTH = 10;

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
        if (dateEntered.length() == DATE_LENGTH) {
            dateEntered += " 00:00:00";
        }
        boolean added = false;
        for (String pat : patterns) {
            try {
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern(pat);
                LocalDateTime localDateTime = LocalDateTime.parse(dateEntered, pattern);
                ZonedDateTime zonedDateTimeLocal = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Warsaw"));
                ZonedDateTime zonedDateTimeUtc = zonedDateTimeLocal.withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime zonedDateTimeLondon = zonedDateTimeLocal.withZoneSameInstant(ZoneId.of("Europe/London"));
                ZonedDateTime zonedDateTimeLosAngeles = zonedDateTimeLocal.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
                ZonedDateTime zonedDateTimeSydney = zonedDateTimeLocal.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
                System.out.println("Czas lokalny: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTimeLocal));
                System.out.println("UTC: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTimeUtc));
                System.out.println("Londyn: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTimeLondon));
                System.out.println("Los Angeles: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTimeLosAngeles));
                System.out.println("Sydney: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTimeSydney));
                added = true;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        if (!added) {
            System.out.println("Niepoprawny format daty.");
        }
    }

    private static String collectingDate(Scanner scanner) {
        System.out.println("Podaj datę:");
        return scanner.nextLine();
    }
}
