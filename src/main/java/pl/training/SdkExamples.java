package pl.training;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class SdkExamples {

    public static void main(String[] args) {
        var bigValue = BigDecimal.ONE;
        var otherBigValue = BigDecimal.valueOf(100.50);
        // new BigDecimal("1.0");
        var result = bigValue.add(otherBigValue);
        System.out.println(result);

        var bigInteger = BigInteger.ONE;

        var text = "a" + "b" + "c";
        var buffer = new StringBuilder() // StringBuffer()
                .append("a")
                .append("b")
                .append("c")
                .toString();

        // Data time

        // Old api
        var date = new Date();
        Calendar calendar = Calendar.getInstance();

        // New api
        LocalDate localDate = LocalDate.now();
        LocalDate birthday = LocalDate.of(1900, 10, 10);
        System.out.println("Today: " + localDate);
        System.out.println("Birthday: " + birthday);

        LocalTime now = LocalTime.now();
        LocalTime meeting = LocalTime.of(14, 15);
        System.out.println("Now: " + now);
        System.out.println("Meeting: " + meeting);

        LocalDateTime timestamp = LocalDateTime.now();
        LocalDateTime event = LocalDateTime.of(localDate, now);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Event: " + event);

        ZonedDateTime localTime = ZonedDateTime.now();
        ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Local time: " + localTime);
        System.out.println("New York time: " + newYorkTime);

        ZonedDateTime systemTime = ZonedDateTime.now();

        // Convert to a specific time zone (e.g., Tokyo)
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoTime = systemTime.withZoneSameInstant(tokyoZone);
        System.out.println("System Time: " + systemTime);
        System.out.println("Tokyo Time: " + tokyoTime);

        Instant instant = Instant.now(); // UTC
        System.out.println("Instant: " + instant);

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 20);
        Duration duration = Duration.between(endTime, startTime);
        System.out.println("Work hours: " + duration.toHours());

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.now();
        Period period = Period.between(startDate, endDate);
        System.out.println("Work period: years: " + period.getYears() + " months: " + period.getMonths() + " days: " + period.getDays());
        System.out.println("Total days: " + ChronoUnit.DAYS.between(startDate, endDate));

        startDate.plusDays(2);
        startDate.plus(2, ChronoUnit.DAYS);
        startTime.isAfter(endTime);
        startDate.isBefore(endDate);

        // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm:ss"); // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        System.out.println("Formatted time: " + formatter.format(timestamp));
        String dateText = "06-02-2025 / 14:44:47";
        LocalDateTime dataTime = LocalDateTime.parse(dateText, formatter);

        DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .toFormatter();

        System.out.println("Custom Formatted Date: " + dataTime.format(customFormatter));

        Date legacyDate = Date.from(instant);
        Instant instantTimestamp = legacyDate.toInstant();
        System.out.println("Legacy Date: " + legacyDate);
    }

}
