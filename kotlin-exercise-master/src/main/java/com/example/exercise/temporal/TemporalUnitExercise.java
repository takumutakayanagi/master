package com.example.exercise.temporal;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class TemporalUnitExercise {

    public static void main(String[] args) {
        new TemporalUnitExercise().tryAll();
    }

    public void tryAll() {
        demoSupportedBy();
        demoAddTo();
        demoBetween();

        demoAdjustInto();
        demoAddTo2();
        demoBetween2();
        demoIsSupporteBy();
    }

    // isSupportedBy(Temporal temporal)
    // 指定された時間的オブジェクトでこの単位がサポートされているかどうかを確認します。
    private void demoSupportedBy() {
        LocalDateTime localDateTime = LocalDateTime.now();
        isSupporteBy("LocalDateTime" , localDateTime);
        LocalDate localDate = LocalDate.now();
        isSupporteBy("localDate" , localDate);
        LocalTime localTime = LocalTime.now();
        isSupporteBy("localTime" , localTime);
        YearMonth yearMonth = YearMonth.now();
        isSupporteBy("yearMonth" , yearMonth);
    }

    private void isSupporteBy(String kind, Temporal temporal) {
        System.out.println("isSupportedBy " + kind);
        System.out.println("years: " + ChronoUnit.YEARS.isSupportedBy(temporal));
        System.out.println("months: " + ChronoUnit.MONTHS.isSupportedBy(temporal));
        System.out.println("days: " + ChronoUnit.DAYS.isSupportedBy(temporal));
        System.out.println("hours: " + ChronoUnit.HOURS.isSupportedBy(temporal));
        System.out.println("minutes: " + ChronoUnit.MINUTES.isSupportedBy(temporal));
        System.out.println("seconds: " + ChronoUnit.SECONDS.isSupportedBy(temporal));
    }

    // addTo(R temporal, long amount)
    // 指定された期間を加算して、指定された時間的オブジェクトのコピーを返します。
    private void demoAddTo() {
        LocalDateTime localDateTime = LocalDateTime.now();
        addTo("LocalDateTime" ,localDateTime, 1L);
        LocalDate localDate = LocalDate.now();
        addTo("localDate" , localDate, 1L);
        LocalTime localTime = LocalTime.now();
        addTo("localTime" , localTime, 1L);
        YearMonth yearMonth = YearMonth.now();
        addTo("yearMonth" , yearMonth, 1L);
    }

    private void addTo(String kind, Temporal temporal, long amount) {
        System.out.println("addTo " + kind);
        if (temporal.isSupported(ChronoField.YEAR)) {
            System.out.println("years: " + ChronoUnit.YEARS.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.YEARS);
        }
        if (temporal.isSupported(ChronoField.MONTH_OF_YEAR)) {
            System.out.println("months: " + ChronoUnit.MONTHS.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.MONTHS);
        }
        if (temporal.isSupported(ChronoField.DAY_OF_MONTH)) {
            System.out.println("days: " + ChronoUnit.DAYS.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.DAYS);
        }
        if (temporal.isSupported(ChronoField.HOUR_OF_DAY)) {
            System.out.println("hours: " + ChronoUnit.HOURS.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.HOURS);
        }
        if (temporal.isSupported(ChronoField.MINUTE_OF_HOUR)) {
            System.out.println("minites: " + ChronoUnit.MINUTES.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.MINUTES);
        }
        if (temporal.isSupported(ChronoField.SECOND_OF_MINUTE)) {
            System.out.println("seconds: " + ChronoUnit.SECONDS.addTo(temporal, amount).toString());
            // temporal.plus(amount, ChronoUnit.SECONDS);
        }
    }

    private void demoBetween() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime exclusive = localDateTime.plusDays(2);
        between("LocalDateTime", localDateTime, exclusive);
    }

    private void between(String kind, Temporal tempInc, Temporal tempExc) {
        System.out.println("between " + kind);
        System.out.println("years: " + ChronoUnit.YEARS.between(tempInc, tempExc));
        tempInc.until(tempExc, ChronoUnit.YEARS);
        System.out.println("months: " + ChronoUnit.MONTHS.between(tempInc, tempExc));
        System.out.println("days: " + ChronoUnit.DAYS.between(tempInc, tempExc));
        System.out.println("hours: " + ChronoUnit.HOURS.between(tempInc, tempExc));
        System.out.println("minutes: " + ChronoUnit.MINUTES.between(tempInc, tempExc));
        System.out.println("seconds: " + ChronoUnit.SECONDS.between(tempInc, tempExc));
    }

    private void demoAdjustInto() {
        LocalDate date = ChronoField.MONTH_OF_YEAR.adjustInto(LocalDate.now(), 12);
        System.out.println(date);

        date = LocalDate.now().with(ChronoField.MONTH_OF_YEAR, 12);
        System.out.println(date);
    }

    private void demoAddTo2() {
        LocalDate date = ChronoUnit.DAYS.addTo(LocalDate.now(), 1);
        System.out.println(date);

        date = LocalDate.now().plus(1, ChronoUnit.DAYS);
        System.out.println(date);

        date = LocalDate.now().plusDays(1);
        System.out.println(date);
    }

    private void demoBetween2() {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(3));
        System.out.println(days);

        days = LocalDate.now().until(LocalDate.now().plusDays(3), ChronoUnit.DAYS);
        System.out.println(days);

        Period period = LocalDate.now().until(LocalDate.now().plusDays(3));
        System.out.println(period.getDays());
    }

    private void demoIsSupporteBy() {
        boolean result;
        result = ChronoUnit.HOURS.isSupportedBy(LocalDateTime.now());
        System.out.println(result);
        result = ChronoUnit.HOURS.isSupportedBy(LocalDate.now());
        System.out.println(result);
        result = ChronoUnit.HOURS.isSupportedBy(LocalTime.now());
        System.out.println(result);
        result = ChronoUnit.HOURS.isSupportedBy(YearMonth.now());
        System.out.println(result);

        result = ChronoUnit.YEARS.isDateBased();
        System.out.println(result);
        result = ChronoUnit.YEARS.isTimeBased();
        System.out.println(result);

        result = ChronoUnit.HOURS.isTimeBased();
        System.out.println(result);
        result = ChronoUnit.HOURS.isTimeBased();
        System.out.println(result);
    }
}
