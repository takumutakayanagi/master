package com.example.exercise.testing

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    UnitTestExercise().tryAll()
}

class UnitTestExercise {

    fun tryAll() {
        println(format(LocalDate.now()))
        println(format(LocalDate.now(), "yyyyMMdd"))
    }

    fun format(date: LocalDate, pattern: String = "yyyy-MM-dd"): String =
        date.format(DateTimeFormatter.ofPattern(pattern))

}