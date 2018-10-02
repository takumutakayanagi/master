package com.example.exercise.temporal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.*;

public class TemporalAccessorExercise {

    public static void main(String[] args) {
        new TemporalAccessorExercise().tryAll();
    }

    public void tryAll() {
        demoRange();
        demoGet();
        demoQuery();
    }

    private void demoRange() {
        LocalDateTime localDateTime = LocalDateTime.now();
        range("LocalDateTime", localDateTime);
    }

    private void range(String kind, Temporal temporal) {
        System.out.println("range " + kind);
        System.out.println("YEAR: " + temporal.range(ChronoField.YEAR).toString());
        System.out.println("MONTH_OF_YEAR: " + temporal.range(ChronoField.MONTH_OF_YEAR).toString());
        System.out.println("DAY_OF_YEAR: " + temporal.range(ChronoField.DAY_OF_YEAR).toString());
        System.out.println("DAY_OF_MONTH: " + temporal.range(ChronoField.DAY_OF_MONTH).toString());
        System.out.println("ALIGNED_WEEK_OF_YEAR: " + temporal.range(ChronoField.ALIGNED_WEEK_OF_YEAR).toString());
        System.out.println("ALIGNED_WEEK_OF_MONTH: " + temporal.range(ChronoField.ALIGNED_WEEK_OF_MONTH).toString());
        System.out.println("DAY_OF_WEEK: " + temporal.range(ChronoField.DAY_OF_WEEK).toString());
    }

    private void demoGet() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int result = localDateTime.get(ChronoField.DAY_OF_MONTH);
        System.out.println(result);
        result = localDateTime.getDayOfMonth();
        System.out.println(result);
    }

    private void demoQuery() {
        // 月の最初の日曜日の週をその月の第1週として
        // 月の第2月曜日、第3木曜日を定休日とするクエリ
        TemporalQuery<Boolean> isRegularHoliday = accessor -> {
            LocalDate target = LocalDate.from(accessor);
            LocalDate first = target.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
            System.out.println(target);
            switch (DayOfWeek.of(target.get(ChronoField.DAY_OF_WEEK))) {
                case MONDAY:
                    if (target.equals(first.plusDays(8))) {
                        return true;
                    }
                    break;
                case THURSDAY:
                    if (target.equals(first.plusDays(18))) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
            return false;
        };

        for (int i=1; i<29; i++) {
            System.out.println(LocalDate.of(2018, 2, i).query(isRegularHoliday));
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate date = localDateTime.query(TemporalQueries.localDate());
        System.out.println(date);

        date = localDateTime.query(LocalDate::from);
        System.out.println(date);

        date = localDateTime.toLocalDate();
        System.out.println(date);
    }
}
