package com.example.exercise.typechecks

fun main(args: Array<String>) {
    TypeChecksExercise().tryAll()
}

/**
 * タイプチェック、キャスト
 *
 * [typecasts] (https://kotlinlang.org/docs/reference/typecasts.html)
 *
 */
class TypeChecksExercise {

    fun tryAll() {
        stringCheck("kotlin")
        stringCheck(123)
    }

    fun stringCheck(obj: Any) {
        if (obj is String) {
            println("$obj is string")
        } else {
            println("obj is not string")
        }
    }

}