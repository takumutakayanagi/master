package com.example.exercise.stdlib

fun main(args: Array<String>) {
    StdLibTextExercise().tryAll()
}

class StdLibTextExercise {

    fun tryAll() {
        demoBuildString()
    }

    // buildString
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/build-string.html

    fun demoBuildString() {
        val str =  buildString {
            append("first line", "\n")
            append("2nd line", "\n")
            append("3rd line", "\n")
        }
        println(str)
    }

}