package com.example.exercise.temporal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterExercise {

    public static void main(String[] args) {
        new TemporalAdjusterExercise().tryAll();
    }

    public void tryAll() {
        demoTemporalAdjuster();
        demoTemporalAdjuster2();
    }

    private void demoTemporalAdjuster() {
        TemporalAdjuster adjuster = temporal -> {
            //時間的オブジェクトを調整する処理
            LocalDate target = LocalDate.from(temporal);

            // 当月の第2月曜日
            LocalDate secondMonday = target
                .with(TemporalAdjusters.firstDayOfMonth())
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
                .plusDays(7);

            if (target.isBefore(secondMonday) || target.isEqual(secondMonday)) {
                return secondMonday;
            }

            // 来月の第2月曜日
            return secondMonday
                .with(TemporalAdjusters.firstDayOfNextMonth())
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
                .plusDays(7);
        };

        Temporal temporal = adjuster.adjustInto(LocalDate.of(2018, 1, 1));
        System.out.println(LocalDate.from(temporal));

        LocalDate date = LocalDate.of(2018, 1, 29).with(adjuster);
        System.out.println(date);
    }

    private void demoTemporalAdjuster2() {
        // 次の月曜日を求める
        LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(nextMonday);
        // 月末
        LocalDate lastDayM = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayM);
        // 年末
        LocalDate lastDayY = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        System.out.println(lastDayY);
    }

}
