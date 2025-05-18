package org.epstesige.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateUtils {
    // Liste des formats possibles
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"),
            DateTimeFormatter.ofPattern("d-M-yyyy")
    );

    public static LocalDateTime parseToLocalDateTime(String dateStr) {
        System.out.println("dateStr: "+dateStr);
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (formatter.toString().contains("H")) {
                    return LocalDateTime.parse(dateStr, formatter);
                } else {
                    // Pour les dates sans heure, on ajoute 00:00:00
                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    return date.atStartOfDay();
                }
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Format de date inconnu : " + dateStr);
    }
}
