package com.example.exercise.ranges

import com.example.exercise.logging.logger

fun main(args: Array<String>) {
    RangeExercise().tryAll()
}

/**
 * 範囲
 *
 * [ranges] (https://kotlinlang.org/docs/reference/ranges.html)
 * [kotlin.ranges] (https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/index.html)
 */
class RangeExercise {
    private val log = logger(RangeExercise::class.java)

    fun tryAll() {
        demoForLoop()
        demoLambdas()
        demoRangeToCollection()
    }

    fun demoForLoop() {
        val start = 1
        val end = 10

        for (i in start..end) {
            println(i)
        }

        for (i in start until end) {
            println(i)
        }

        for (i in end downTo start) {
            println(i)
        }

        for (i in  start..end step 3) {
            println(i)
        }
    }

    fun demoLambdas() {
        val start = 1
        val end = 10

        (start..end).forEach {
            println(it)
        }

        (start..end).reversed().forEach {
            println(it)
        }

        (start..end step 3).forEach {
            println(it)
        }

        (start..end).step(3).forEach {
            println(it)
        }

        (start..end).filter { it % 2 == 0 }.forEach {
            even -> println(even)
        }

        // Kotlin 1.1
        (start..end).onEach {
            println(it)
        }
    }

    fun demoRangeToCollection() {
        val a = arrayOf('a'..'z').flatMap { it }
        // コレクションにはflattenがある
        val b = listOf('A'..'Z').flatten()
        val c = a.plus(b) // a + b
        a.joinToString(", ","[","]").also { println(it) }
        b.joinToString(", ","[","]").also { println(it) }
        c.joinToString(", ","[","]").also { println(it) }
    }
}