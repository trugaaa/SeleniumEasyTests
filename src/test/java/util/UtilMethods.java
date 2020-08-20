package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilMethods {
    public static String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    public static int getCurrentYear() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime currentTime = LocalDateTime.now();
        return Integer.parseInt(dateTimeFormatter.format(currentTime));
    }
}
