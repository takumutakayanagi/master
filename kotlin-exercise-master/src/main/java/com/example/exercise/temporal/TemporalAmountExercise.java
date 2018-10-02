package com.example.exercise.temporal;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.Temporal;

public class TemporalAmountExercise {

    public static void main(String[] args) {
        new TemporalAmountExercise().tryAll();
    }

    public void tryAll() {
        demoAddTo();
        demoSubFrom();
        demoPeriod();
        demoDuration();
    }

    private void demoAddTo() {
        Period daysOfLimit = Period.ofDays(10);
        Temporal temporal = daysOfLimit.addTo(LocalDate.of(2018, 1, 1));
        System.out.println(LocalDate.from(temporal));

        System.out.println(LocalDate.of(2018, 1, 1).plus(daysOfLimit));

        Period period = Period.ofMonths(1).plusDays(10);
        System.out.println(LocalDate.now().plus(period));
    }

    private void demoSubFrom() {
        Period daysOfLimit = Period.ofDays(10);
        Temporal temporal = daysOfLimit.subtractFrom(LocalDate.of(2018, 2, 1));
        System.out.println(LocalDate.from(temporal));

        System.out.println(LocalDate.of(2018, 2, 1).minus(daysOfLimit));
    }

    private void demoPeriod() {
        Period period = Period.ofDays(1);
        System.out.println(LocalDate.now().plus(period));

        period = Period.ofWeeks(1);
        System.out.println(LocalDate.now().plus(period));

        period = Period.ofMonths(1).plusDays(10);
        System.out.println(LocalDate.now().plus(period));
    }

    private void demoDuration() {
        Duration duration = Duration.ofHours(1);
        System.out.println(LocalDateTime.now().plus(duration));

        duration = Duration.ofMinutes(30);
        System.out.println(LocalDateTime.now().plus(duration));

        duration = Duration.ofDays(2);
        System.out.println(LocalDateTime.now().plus(duration));

        duration = Duration.between(
            LocalDateTime.of(2018, 1, 1, 0, 0, 0),
            LocalDateTime.of(2018, 1, 2, 1, 2, 3)
        );
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.getSeconds());
    }
}
