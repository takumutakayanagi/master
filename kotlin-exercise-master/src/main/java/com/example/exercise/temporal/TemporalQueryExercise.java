package com.example.exercise.temporal;

import java.time.*;
import java.time.temporal.*;

public class TemporalQueryExercise {

    public static void main(String[] args) {
        new TemporalQueryExercise().tryAll();
    }

    public void tryAll() {
        demoQuery();
        demoQueries();
    }

    private void demoQuery() {
        TemporalQuery<Long> daysOfNextMonday = accessor -> {
            LocalDate inc = LocalDate.from(accessor);
            LocalDate exc = inc.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            return ChronoUnit.DAYS.between(inc, exc);
        };

        Long days = LocalDate.of(2018, 1, 29).query(daysOfNextMonday);
        System.out.println("days: " + days);

        Long result = LocalDate.of(2018, 1, 29).query( accessor -> {
            LocalDate inc = LocalDate.from(accessor);
            LocalDate exc = inc.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            return ChronoUnit.DAYS.between(inc, exc);
        });
        System.out.println(result);
    }

    private void demoQueries() {
        Temporal temporal = LocalDateTime.of(2018, 12, 31, 22, 9, 15);

        LocalDate date = TemporalQueries.localDate().queryFrom(temporal);
        System.out.println(date);

        LocalDate date2 = temporal.query(TemporalQueries.localDate());
        System.out.println(date2);

        LocalDate date3 = temporal.query(LocalDate::from);
        System.out.println(date3);

        LocalTime time = TemporalQueries.localTime().queryFrom(temporal);
        System.out.println(time);

        // public <R> R query(TemporalQuery<R> query)
        LocalTime time2 = temporal.query(TemporalQueries.localTime());
        System.out.println(time2);

        // public <R> R query(TemporalQuery<R> query)
        LocalTime time3 = temporal.query(LocalTime::from);
        System.out.println(time3);

        LocalDateTime dateTime3 = temporal.query(LocalDateTime::from);
        System.out.println(dateTime3);

        YearMonth ym = temporal.query(YearMonth::from);
        System.out.println(ym);

        Month m = temporal.query(Month::from);
        System.out.println(m);

        // 実行時エラー
        // Exception in thread "main" java.time.DateTimeException: Unable to obtain LocalDateTime from TemporalAccessor: 2018-12-31 of type java.time.LocalDate
        //LocalDateTime dt = LocalDate.of(2018, 12, 31).query(LocalDateTime::from);
        //System.out.println(dt);
    }

}
