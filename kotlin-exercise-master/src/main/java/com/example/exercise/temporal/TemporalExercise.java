package com.example.exercise.temporal;

import java.time.*;
import java.time.temporal.*;

public class TemporalExercise {

    public static void main(String[] args) {
        new TemporalExercise().tryAll();
    }

    public void tryAll() {
        demoMinus();
        demoPlus();
        demoUntil();
        demoWith();

        demoDuration();
    }

    private void demoMinus() {
        Period period = Period.ofDays(10);
        LocalDate date = LocalDate.of(2018, 1, 30).minus(period);
        System.out.println(date);

        date = LocalDate.of(2018, 1, 30).minus(10, ChronoUnit.DAYS);
        System.out.println(date);

        date = LocalDate.of(2018, 1, 30).minusDays(10);
        System.out.println(date);
    }

    private void demoPlus() {
        Period period = Period.ofDays(10);
        LocalDate date = LocalDate.of(2018, 1, 30).plus(period);
        System.out.println(date);

        date = LocalDate.of(2018, 1, 30).plus(10, ChronoUnit.DAYS);
        System.out.println(date);

        date = LocalDate.of(2018, 1, 30).plusDays(10);
        System.out.println(date);
    }

    private void demoUntil() {
        LocalDate date = LocalDate.of(2018, 1, 1);
        LocalDate endExclusive = LocalDate.of(2019, 1, 1);

        Long result = date.until(endExclusive, ChronoUnit.DAYS);
        System.out.println("until days:" + result);

        result = date.until(endExclusive, ChronoUnit.MONTHS);
        System.out.println("until months:" + result);

        result = date.until(endExclusive, ChronoUnit.YEARS);
        System.out.println("until years:" + result);
    }

    private void demoWith() {
        LocalDate date = LocalDate.of(2018, 1, 1);
        LocalDate result = date.with(ChronoField.MONTH_OF_YEAR, 3);
        System.out.println(result);

        date = LocalDate.of(2018, 1, 1).withMonth(3);
        System.out.println(date);
    }

    private void demoDuration() {
        LocalDateTime d1 = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2017, 3, 3, 0, 0, 0);

        // A time-based amount of time, such as '34.5 seconds'.
        Duration duration = Duration.between(d1, d2);
        long count = duration.toDays();
        System.out.println("duration days:" + count);

        // A date-based amount of time in the ISO-8601 calendar system,
        // such as '2 years, 3 months and 4 days'.
        Period period = Period.between(d1.toLocalDate(), d2.toLocalDate());
        count = period.getDays();
        System.out.println("period days:" + count);

        count = period.getMonths();
        System.out.println("period months:" + count);

        count = period.getYears();
        System.out.println("period years:" + count);

        // A standard set of date periods units.
        count = ChronoUnit.DAYS.between(d1, d2);
        System.out.println("days:" + count);

        count = ChronoUnit.MONTHS.between(d1, d2);
        System.out.println("months:" + count);

        count = ChronoUnit.YEARS.between(d1 ,d2);
        System.out.println("years:" + count);
    }

}
