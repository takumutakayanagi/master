package com.example.exercise.nullsafe

import java.time.LocalDate

fun <T : Any> T?.notNull(actions: (T) -> Unit) {
    if (this != null) actions(this)
}

fun main(args: Array<String>) {
    NullSafetyExercise().tryAll()
}

class NullSafetyExercise {

    fun tryAll() {
        demoNotNull("xxx")
        demoNotNull(null)

        demoLetWithNotNull("xxx")
        demoLetWithNotNull(null)
    }

    fun demoNotNull(s: String?) {
        s.notNull {
            println("notNull: $it")
        }
    }

    fun demoLetWithNotNull(s: String?) {
        s?.let {
            println("let: $it")
        }
    }

    var d1: LocalDate? = null
    val d2: LocalDate? = null

    fun demoSmartCast() {
        val n = LocalDate.now()
        if (d1 != null) {
            // d1.compareTo(n) compile error
            d1!!.compareTo(n)
        }
        if (d2 != null) {
            d2.compareTo(n)
        }
    }

}